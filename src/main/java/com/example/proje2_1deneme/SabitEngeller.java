package com.example.proje2_1deneme;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proje2_1deneme.Main.*;
import static com.example.proje2_1deneme.Main.KARE_BOYUTU;

public class SabitEngeller extends Engeller {

    private int mevsim;   // Yaz için = 1 && Kış için = 2

    public SabitEngeller(String imagePath, String ad, int engelX, int engelY, int engelBoy, int engelGenislik, int mevsim) {
        super(imagePath, ad, engelX, engelY, engelBoy, engelGenislik);
        this.mevsim = mevsim;
    }


    static SabitEngeller Agac = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Ağaç.png", 0, 0, 2, 2, 1);

    static SabitEngeller Dag = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Dağ.png", 0, 0, 6, 6, 1);

    static SabitEngeller DagKis = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "DağKış.png", 0, 0, 6, 6, 2);

    static SabitEngeller Duvar = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Duvar.png", 0, 0, 6, 6, 1);

    static SabitEngeller OrmanKis = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "KarlıOrman.png", 0, 0, 6, 6, 2);

    static SabitEngeller Kaya = new SabitEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Kaya.png", 0, 0, 6, 6, 1);

    static SabitEngeller[] sabitEngeller = {Agac, Dag, DagKis, Duvar, OrmanKis, Kaya};
    static List<SabitEngeller> sabitEngellerArrayList = new ArrayList<>();
    static List<ImageView> sabitEngelImageViews = new ArrayList<>();


    public static void sabitEngelOlustur(Group root) throws CloneNotSupportedException {

        // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra sabit engellerin oluşmamasını sağlıyor
        root.getChildren().removeAll(sabitEngelImageViews);
        sabitEngelImageViews.clear();
        sabitEngellerArrayList.clear();

        int engelX, engelY;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int a = random.nextInt(sabitEngeller.length);
            SabitEngeller yerlestirilecekSabitEngel = (SabitEngeller) sabitEngeller[a].clone();

            if(yerlestirilecekSabitEngel.mevsim == 1){
                engelX = (int) ((Math.random() * KARE_YUKSEKLIK/2) + KARE_YUKSEKLIK/2);
                engelY = (int) (Math.random() * KARE_GENISLIK);
            }else{
                engelX = (int) (Math.random() * KARE_YUKSEKLIK / 2);
                engelY = (int) (Math.random() * KARE_GENISLIK);
            }

            // Çakışma kontrolü
            boolean overlap = false;
            for (SabitEngeller existingEngel : sabitEngellerArrayList) {
                if (Math.abs(existingEngel.getEngelX() - engelX) < 2 && Math.abs(existingEngel.getEngelY() - engelY) < 2) {
                    overlap = true;
                    break;
                }
            }

            // Çakışma yoksa engeli ekle
            if (!overlap) {
                yerlestirilecekSabitEngel.setEngelX(engelX);
                yerlestirilecekSabitEngel.setEngelY(engelY);
                sabitEngellerArrayList.add(yerlestirilecekSabitEngel);
            } else {
                i--; // Çakışma varsa i'yi azalt
            }
        }

        for (SabitEngeller sabitEngel : sabitEngellerArrayList) {
            Image imageSabitEngel = new Image(sabitEngel.getImagePath() + sabitEngel.getAd());
            ImageView imageView = new ImageView(imageSabitEngel);
            imageView.setId(sabitEngel.getAd());

            imageView.setFitWidth(KARE_BOYUTU * sabitEngel.getEngelGenislik());
            imageView.setFitHeight(KARE_BOYUTU * sabitEngel.getEngelBoy());
            imageView.setX(sabitEngel.getEngelX() * KARE_BOYUTU);
            imageView.setY(sabitEngel.getEngelY() * KARE_BOYUTU);

            sabitEngelImageViews.add(imageView);
            root.getChildren().add(imageView);
        }
    }
}
