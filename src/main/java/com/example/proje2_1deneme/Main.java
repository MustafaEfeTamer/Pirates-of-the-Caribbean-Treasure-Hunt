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
import javafx.stage.Stage;

import static com.example.proje2_1deneme.DinamikEngeller.hareketEttir;
import static com.example.proje2_1deneme.DinamikEngeller.hareketliEngelOlustur;
import static com.example.proje2_1deneme.Karakter.karakterOlustur;
//import static com.example.proje2_1deneme.Lokasyon.cisimOlustur;
import static com.example.proje2_1deneme.SabitEngeller.sabitEngelOlustur;

public class Main extends Application {

    static final int GENISLIK = 800;
    static final int YUKSEKLIK = 800;
    static final int KARE_YUKSEKLIK = 40;
    static final int KARE_GENISLIK = 40;
    static final int KARE_BOYUTU = GENISLIK / KARE_YUKSEKLIK;

    private GraphicsContext gc;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws CloneNotSupportedException {
        primaryStage.setTitle("OTONOM HAZİNE AVCISI");

        // Canvas ve Butonlar için VBox oluştur
        Group root = new Group();
        Canvas canvas = new Canvas(GENISLIK, YUKSEKLIK);
        root.getChildren().add(canvas);

        // "Yeni harita oluştur" ve "Başlat" butonlarını oluştur
        Button yeniHaritaButon = new Button("Yeni harita oluştur");
        Button baslatButon = new Button("Başlat");

        // Butonların rengini değiştirmek için CSS özellikleri atıyorum
        yeniHaritaButon.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white;");
        baslatButon.setStyle("-fx-background-color: #00ff00; -fx-text-fill: white;");

        // Butonların tıklanma olaylarını tanımla
        yeniHaritaButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Canvas ve Butonlar için VBox oluştur

                /*Group root = new Group();
                Canvas canvas = new Canvas(GENISLIK, YUKSEKLIK);
                root.getChildren().add(canvas);
                */
                // Yeni harita oluşturma işlemleri buraya gelebilir
                drawBackground(gc);
                try {
                    sabitEngelOlustur(root);
                    hareketliEngelOlustur(root);
                    //cisimOlustur(root);
                    karakterOlustur(root);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                run();
            }
        });

        baslatButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Başlatma işlemleri buraya gelebilir
                hareketEttir();
                run();
            }
        });

        // Butonları HBox'a ekle
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(yeniHaritaButon, baslatButon);
        buttonBox.setLayoutX(10);
        buttonBox.setLayoutY(10);
        root.getChildren().add(buttonBox);

        // Butonları VBox'a ekle
       // root.getChildren().addAll(yeniHaritaButon, baslatButon);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        hareketliEngelOlustur(root);
        sabitEngelOlustur(root);
        //cisimOlustur(root);
        karakterOlustur(root);
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
                    gc.setFill(Color.web("#008000"));
                } else {
                    gc.setFill(Color.web("#ffffff"));
                }
                gc.fillRect(i * KARE_BOYUTU, j * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
            }
        }
    }
}