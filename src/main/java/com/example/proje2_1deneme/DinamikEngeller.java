package com.example.proje2_1deneme;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proje2_1deneme.Main.*;

public class DinamikEngeller extends Engeller{

    private String yon;
    private int hareketFrenkansi;

    public DinamikEngeller(String imagePath, String ad, int engelX, int engelY, int engelBoy, int engelGenislik, String yon, int hareketFrenkansi) {
        super(imagePath, ad, engelX, engelY, engelBoy, engelGenislik);
        this.hareketFrenkansi = hareketFrenkansi;
        this.yon = yon;
    }


    static DinamikEngeller kus = new DinamikEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Kuş.png", 0, 0, 3, 3, "Y", 1);
    static DinamikEngeller ari = new DinamikEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Arı.png", 0, 0, 3, 3, "X", 1);
    static DinamikEngeller[] dinamikEngeller = {kus, ari};
    static List<DinamikEngeller> hareketliEngelArrayList = new ArrayList<>();
    static List<ImageView> hareketliEngelImageViews = new ArrayList<>();


    public static void hareketliEngelOlustur(Group root) throws CloneNotSupportedException {

        // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra dinamik engel oluşmamasını sağlıyor
        root.getChildren().removeAll(hareketliEngelImageViews);
        hareketliEngelImageViews.clear();
        hareketliEngelArrayList.clear();

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int a = random.nextInt(dinamikEngeller.length);
            DinamikEngeller yerlestirilecekHareketliEngel = (DinamikEngeller) dinamikEngeller[a].clone();
            int engelX = (int) (Math.random() * KARE_YUKSEKLIK);
            int engelY = (int) (Math.random() * KARE_GENISLIK);
            yerlestirilecekHareketliEngel.setEngelX(engelX);
            yerlestirilecekHareketliEngel.setEngelY(engelY);
            hareketliEngelArrayList.add(yerlestirilecekHareketliEngel);
        }

        for (DinamikEngeller hareketliEngel : hareketliEngelArrayList) {
            Image imageHareketliEngel = new Image(hareketliEngel.getImagePath() + hareketliEngel.getAd());
            ImageView imageView = new ImageView(imageHareketliEngel);
            imageView.setId(hareketliEngel.getAd());

            imageView.setFitWidth(KARE_BOYUTU * hareketliEngel.getEngelGenislik());
            imageView.setFitHeight(KARE_BOYUTU * hareketliEngel.getEngelBoy());
            imageView.setX(hareketliEngel.getEngelX() * KARE_BOYUTU);
            imageView.setY(hareketliEngel.getEngelY() * KARE_BOYUTU);

            hareketliEngelImageViews.add(imageView);
            root.getChildren().add(imageView);
        }
    }


    public static void hareketEttir() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView imageView : hareketliEngelImageViews) {

                    double x = imageView.getX();
                    double y = imageView.getY();

                    for (DinamikEngeller hareketliEngel : hareketliEngelArrayList) {
                        if (imageView.getId().equals(hareketliEngel.getAd())) {
                            switch (hareketliEngel.yon) {

                                case "Y":
                                    y += hareketliEngel.hareketFrenkansi;
                                    if (y >= KARE_YUKSEKLIK * KARE_BOYUTU - hareketliEngel.hareketFrenkansi || y < 0) {
                                        hareketliEngel.hareketFrenkansi *= -1; // Change direction
                                    }
                                    break;
                                case "X":
                                    x += hareketliEngel.hareketFrenkansi;
                                    if (x >= KARE_GENISLIK * KARE_BOYUTU - hareketliEngel.hareketFrenkansi || x < 0) {
                                        hareketliEngel.hareketFrenkansi *= -1; // Change direction
                                    }
                                    break;
                            }

                            imageView.setX(x);
                            imageView.setY(y);
                        }
                    }
                }
            }
        };
        timer.start();
    }
}
