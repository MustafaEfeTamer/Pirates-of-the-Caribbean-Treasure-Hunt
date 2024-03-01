package com.example.proje2_1deneme;

import com.example.proje2_1deneme.HareketsizEngeller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.util.Random;

import static com.example.proje2_1deneme.HareketliEngeller.hareketEttir;
import static com.example.proje2_1deneme.HareketliEngeller.hareketliEngelOlustur;

public class Main extends Application {

    static final int GENISLIK = 800;
    static final int YUKSEKLIK = 800;
    static final int KARE_YUKSEKLIK = 40;
    static final int KARE_GENISLIK = 40;
    static final int KARE_BOYUTU = GENISLIK / KARE_YUKSEKLIK;

    private GraphicsContext gc;

    private Image imageYazEngel;
    private Image imageYazEngel2;
    private Image imageYazEngel3;
    private Image imageYazEngel4;
    private Image imageYazEngel5;
    private Image imageYazEngel6;
    private Image imageKarakter;
    private Image imageDinamikEngel;
    private Image imageDinamikEngel2;


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

        // Butonların tıklanma olaylarını tanımla
        yeniHaritaButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Canvas ve Butonlar için VBox oluştur

                /*Group root = new Group();
                Canvas canvas = new Canvas(GENISLIK, YUKSEKLIK);
                root.getChildren().add(canvas);*/

                // Yeni harita oluşturma işlemleri buraya gelebilir
                drawBackground(gc);
                YazEngelOlustur();
                try {
                    hareketliEngelOlustur(root);
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

        // Butonları VBox'a ekle
        root.getChildren().addAll(yeniHaritaButon, baslatButon);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        YazEngelOlustur();
        hareketliEngelOlustur(root);
        run();
    }

    private void run() {
        drawBackground(gc);
        EngelCiz(gc);
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

    private void YazEngelOlustur() {
        String imagePath = "file:///C:\\Users\\musta\\Desktop\\Engeller/";
        imageYazEngel = new Image(imagePath + Dağ.dag);
        imageYazEngel2 = new Image(imagePath + Duvar.duvar);
        imageYazEngel3 = new Image(imagePath + Ağaç.agac);
        imageYazEngel4 = new Image(imagePath + Kaya.kaya);
        imageYazEngel5 = new Image(imagePath + KarlıOrman.karliOrman);
        imageYazEngel6 = new Image(imagePath + DağKış.karliDag);
        imageKarakter = new Image(imagePath + Karakter.karakterResim);
    }

    private void EngelCiz(GraphicsContext gc) {
        ArrayList<Lokasyon> coordinates = new ArrayList<>(); // Engellerin koordinatlarını tutacak ArrayList
        Random random = new Random();
        int x, y;

        // Her bir engel çizilmeden önce koordinatların karıştırılması
        for (int i = 0; i < 9; i++) { // Toplamda engel sayısı kadar "i"
            if (i >= 0 && i < 4) {
                x = (random.nextInt(KARE_GENISLIK/2) + KARE_GENISLIK/2); // Sağ tarafta çizmek için x koordinatı
                y = (random.nextInt(KARE_YUKSEKLIK)); // Rastgele y koordinatı seçme
            } else if (i >= 4 && i < 6) {
                x = (random.nextInt(KARE_YUKSEKLIK/2)); // sol tarafta çizmek için x koordinatı seçme
                y = (random.nextInt(KARE_GENISLIK)); // Rastgele y koordinatı seçme
            } else {
                x = (int) (Math.random() * KARE_YUKSEKLIK); // Rastgele x koordinatı seçme
                y = (int) (Math.random() * KARE_GENISLIK); // Rastgele y koordinatı seçme
            }

            // Daha önce seçilen koordinatlarla çakışıp çakışmadığını kontrol etme
            boolean isOverlap = false;
            for (Lokasyon lokasyon : coordinates) {
                if (lokasyon.getX() == x && lokasyon.getY() == y) {
                    isOverlap = true;
                    break;
                }
            }

            // Eğer çakışma yoksa, bu koordinatları kullanarak engeli çiz
            if (!isOverlap) {
                coordinates.add(new Lokasyon(i, x, y)); // Koordinatları ArrayList'e ekleme
                // Engeli çizme işlemi
                if (i == 0)
                    gc.drawImage(imageYazEngel, x * KARE_BOYUTU, y * KARE_BOYUTU, 140, 140);
                else if (i == 1)
                    gc.drawImage(imageYazEngel2, x * KARE_BOYUTU, y * KARE_BOYUTU, 40, 40);
                else if (i == 2)
                    gc.drawImage(imageYazEngel3, x * KARE_BOYUTU, y * KARE_BOYUTU, 60, 60);
                else if (i == 3)
                    gc.drawImage(imageYazEngel4, x * KARE_BOYUTU, y * KARE_BOYUTU, 40, 40);
                else if (i == 4)
                    gc.drawImage(imageYazEngel5, x * KARE_BOYUTU, y * KARE_BOYUTU, 120, 120);
                else if (i == 5)
                    gc.drawImage(imageYazEngel6, x * KARE_BOYUTU, y * KARE_BOYUTU, 120, 120);
                else if (i == 6)
                    gc.drawImage(imageDinamikEngel, x * KARE_BOYUTU, y * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
                else if (i == 7)
                    gc.drawImage(imageDinamikEngel, x * KARE_BOYUTU, y * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
                else if (i == 8)
                    gc.drawImage(imageDinamikEngel2, x * KARE_BOYUTU, y * KARE_BOYUTU, 60, 60);
            } else {
                i--; // Eğer çakışma varsa, i'yi azaltarak tekrar deneme yapılmasını sağla
            }
        }
        // Karakteri çizmek için ayrı bir koordinat seçme
        int karakterX = (int) (Math.random() * KARE_YUKSEKLIK);
        int karakterY = (int) (Math.random() * KARE_GENISLIK);
        gc.drawImage(imageKarakter, karakterX * KARE_BOYUTU, karakterY * KARE_BOYUTU, 40, 40);
    }
}