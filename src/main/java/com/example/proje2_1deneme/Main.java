package com.example.proje2_1deneme;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.example.proje2_1deneme.DinamikEngeller.*;
import static com.example.proje2_1deneme.Hazine.cisimOlustur;
import static com.example.proje2_1deneme.Karakter.*;
import static com.example.proje2_1deneme.SabitEngeller.sabitEngelOlustur;

public class Main extends Application {

    static final int GENISLIK = 800;
    static final int YUKSEKLIK = 800;
    static final int KARE_YUKSEKLIK = 40;
    static final int KARE_GENISLIK = 40;
    static final int KARE_BOYUTU = GENISLIK / KARE_YUKSEKLIK;

    static GraphicsContext gc;
    static Canvas kutuCanvas;
    static GraphicsContext kutuGc;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws CloneNotSupportedException {
        primaryStage.setTitle("KARAYIP KORSANLARI🏴‍☠️");

        // Canvas ve Butonlar için VBox oluştur
        Group root = new Group();
        Canvas canvas = new Canvas(GENISLIK, YUKSEKLIK);
        root.getChildren().add(canvas);

        // kutuCanvas kutucuğunun genişlik boyutunu ve yükseklik boyutunu ayarlar
        kutuCanvas = new Canvas(420, 50);
        // kutuCanvas'ı root grubuna ekle
        root.getChildren().add(kutuCanvas);
        // kutuCanvas'ın konumunu ayarlar
        kutuCanvas.setLayoutX(380);
        kutuCanvas.setLayoutY(5);
        // kutuCanvas'ın arka plan rengini ayarlar
        kutuCanvas.setStyle("-fx-background-color: #E9967A;");
        // kutuGc'nin yazı rengini, yazı tipini, ve kalınlığını ayarlar
        kutuGc = kutuCanvas.getGraphicsContext2D();
        kutuGc.setFill(Color.RED);
        kutuGc.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        // "Yeni harita oluştur" ve "Başlat" butonlarını oluştur
        Button yeniHaritaButon = new Button("Yeni harita oluştur");
        Button baslatButon = new Button("Başlat");
        Button sonucButon = new Button("Sonuç");

        // Butonların rengini değiştirmek için CSS özellikleri atıyorum
        yeniHaritaButon.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white;");
        baslatButon.setStyle("-fx-background-color: #00ff00; -fx-text-fill: white;");
        sonucButon.setStyle("-fx-background-color: #0000ff; -fx-text-fill: white;");

        // Butonların tıklanma olaylarını tanımla
        yeniHaritaButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawBackground(gc);
                try {
                    hareketliEngelOlustur(root);
                    sabitEngelOlustur(root);
                    karakterOlustur(root);
                    cisimOlustur(root);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                run();
            }
        });

        baslatButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* enKisaYolBul methodunda karakterin 'x' ve 'y' koordinatlarını değiştiriyoruz. Böylece son hali ile ilk hali aynı olmuyor
                 bu yüzden startX ve startY değerlerimizle her şey bittikten sonra başlat butonuna bastığımızda x ve y değerlerini eski haline getiriyoruz.*/
                karakter.setX(karakter.getStartX());
                karakter.setY(karakter.getStartY());
                // Başlatma işlemleri buraya gelebilir
                hareketEttir();
                karakterHareket(root);
                run();
            }
        });

        sonucButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SonucArayuz sonucArayuz = new SonucArayuz();
                drawBackground(gc);
                try {
                    sonucArayuz.start(new Stage());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                run();
            }
        });

        // Butonları HBox'a ekle
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(yeniHaritaButon, baslatButon, sonucButon);
        buttonBox.setLayoutX(10);
        buttonBox.setLayoutY(10);
        root.getChildren().add(buttonBox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        hareketliEngelOlustur(root);
        sabitEngelOlustur(root);
        karakterOlustur(root);
        cisimOlustur(root);
        run();
    }

    private void run() {
        drawBackground(gc);
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < KARE_YUKSEKLIK/2; i++) {
            for (int j = 0; j < KARE_GENISLIK; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("#808080"));
                } else {
                    gc.setFill(Color.web("#ffffff"));
                }
                gc.fillRect(i * KARE_BOYUTU, j * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
            }
        }

        for (int i = KARE_YUKSEKLIK/2; i < KARE_YUKSEKLIK; i++) {
            for (int j = 0; j < KARE_GENISLIK; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("#66ff00"));
                } else {
                    gc.setFill(Color.web("#ffffff"));
                }
                gc.fillRect(i * KARE_BOYUTU, j * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
            }
        }
    }

    // karakterin geçtiği izlediği yolu yeşil renge boyar
    public static void drawCharacterPath(GraphicsContext gc, int karakterX, int karakterY){
        gc.setFill(Color.web("#008000"));
        gc.fillRect(karakterX * KARE_BOYUTU, karakterY * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
    }
    // dinamik engellerin izlediği yolu kırmızıya boyar
    public static void drawDynamicObstaclePath(GraphicsContext gc, int hareketliEngelX, int hareketliEngelY){
        gc.setFill(Color.web("FF0000"));
        gc.fillRect(hareketliEngelX * KARE_BOYUTU, hareketliEngelY * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
    }
}