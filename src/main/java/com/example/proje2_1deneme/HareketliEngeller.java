package com.example.proje2_1deneme;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proje2_1deneme.Main.*;

public class HareketliEngeller extends Engeller{

    private String yon;
    private int boyut;


    public HareketliEngeller(String imagePath, String ad, int engelX, int engelY, int engelBoy, int engelGenislik, String yon, int boyut) {
        super(imagePath, ad, engelX, engelY, engelBoy, engelGenislik);
        this.boyut = boyut;
        this.yon = yon;
    }


    static HareketliEngeller kus = new HareketliEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Kuş.png", 0, 0, 2, 2, "Y", 5);
    static HareketliEngeller ari = new HareketliEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Arı.png", 0, 0, 6, 6, "X", 3);
    static HareketliEngeller[] hareketliEngeller = {kus, ari};
    static List<HareketliEngeller> hareketliEngelArrayList = new ArrayList<>();
    static List<ImageView> hareketliEngelImageViews = new ArrayList<>();


    public static void hareketliEngelOlustur(Group root) throws CloneNotSupportedException {

        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int a = random.nextInt(hareketliEngeller.length);
            HareketliEngeller yerlestirilecekHareketliEngel = (HareketliEngeller) hareketliEngeller[a].clone();
            int engelX = (int) (Math.random() * KARE_YUKSEKLIK);
            int engelY = (int) (Math.random() * KARE_GENISLIK);
            yerlestirilecekHareketliEngel.setEngelX(engelX);
            yerlestirilecekHareketliEngel.setEngelY(engelY);
            hareketliEngelArrayList.add(yerlestirilecekHareketliEngel);
        }

        for (HareketliEngeller hareketliEngel : hareketliEngelArrayList) {
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

                    for (HareketliEngeller hareketliEngel : hareketliEngelArrayList) {
                        if (imageView.getId().equals(hareketliEngel.getAd())) {
                            switch (hareketliEngel.yon) {

                                case "Y":
                                    y += hareketliEngel.boyut;
                                    if (y >= KARE_YUKSEKLIK * KARE_BOYUTU - hareketliEngel.boyut || y < 0) {
                                        hareketliEngel.boyut *= -1; // Change direction
                                    }
                                    break;
                                case "X":
                                    x += hareketliEngel.boyut;
                                    if (x >= KARE_GENISLIK * KARE_BOYUTU - hareketliEngel.boyut || x < 0) {
                                        hareketliEngel.boyut *= -1; // Change direction
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
