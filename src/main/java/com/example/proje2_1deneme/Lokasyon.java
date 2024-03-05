/*
package com.example.proje2_1deneme;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proje2_1deneme.Main.*;
import static com.example.proje2_1deneme.Main.KARE_BOYUTU;

public class Lokasyon {
    private String imagePath;
    private String sandik;
    private int koordinatX;
    private int koordinatY;
    private int sandikBoy;
    private int sandikGenislik;

    public Lokasyon(String imagePath, String sandik, int koordinatX, int koordinatY, int sandikBoy, int sandikGenislik) {
        this.imagePath = imagePath;
        this.sandik = sandik;
        this.koordinatX = koordinatX;
        this.koordinatY = koordinatY;
        this.sandikBoy = sandikBoy;
        this.sandikGenislik = sandikGenislik;
    }

    static Lokasyon altinSandik = new Lokasyon("file:///C:\\Users\\musta\\Desktop\\Engeller/", "AltınSandık.png", 0, 0, 2, 2);
    static Lokasyon gumusSandik = new Lokasyon("file:///C:\\Users\\musta\\Desktop\\Engeller/", "GümüşSandık.png", 0, 0, 2, 2);

    static Lokasyon[] cisimler = {altinSandik, gumusSandik};
    static List<Lokasyon> cisimlerArrayList = new ArrayList<>();
    static List<ImageView> cisimImageViews = new ArrayList<>();

    public String getImagePath() {
        return imagePath;
    }

    public static void cisimOlustur(Group root) throws CloneNotSupportedException {

        // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra hazinelerin oluşmamasını sağlıyor
        root.getChildren().removeAll(cisimImageViews);
        cisimImageViews.clear();
        cisimlerArrayList.clear();
        int engelX, engelY;
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int a = random.nextInt(cisimler.length);
            Lokasyon yerlestirilecekCisim = (Lokasyon) cisimler[a].clone();
            engelX = (int) (Math.random() * KARE_YUKSEKLIK);
            engelY = (int) (Math.random() * KARE_GENISLIK);

            // Çakışma kontrolü
            boolean overlap = false;
            for (Lokasyon cisim : cisimlerArrayList) {
                if (Math.abs(cisim.getKoordinatX() - engelX) < 2 && Math.abs(cisim.getKoordinatY() - engelY) < 2) {
                    overlap = true;
                    break;
                }
            }

            // Çakışma yoksa engeli ekle
            if (!overlap) {
                yerlestirilecekCisim.setKoordinatX(engelX);
                yerlestirilecekCisim.setKoordinatY(engelY);
                cisimlerArrayList.add(yerlestirilecekCisim);
            } else {
                i--; // Çakışma varsa i'yi azalt
            }
        }

        for (Lokasyon cisim : cisimlerArrayList) {
            Image imageCisim = new Image(cisim.getImagePath() + cisim.getSandik());
            ImageView imageView = new ImageView(imageCisim);
            imageView.setId(cisim.getSandik());

            imageView.setFitWidth(KARE_BOYUTU * cisim.getSandikGenislik());
            imageView.setFitHeight(KARE_BOYUTU * cisim.getSandikBoy());
            imageView.setX(cisim.getKoordinatX() * KARE_BOYUTU - (imageView.getFitWidth() / 2));
            imageView.setY(cisim.getKoordinatY() * KARE_BOYUTU - (imageView.getFitHeight() / 2));

            cisimImageViews.add(imageView);
            root.getChildren().add(imageView);
        }
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSandik() {
        return sandik;
    }

    public void setSandik(String sandik) {
        this.sandik = sandik;
    }

    public int getKoordinatX() {
        return koordinatX;
    }

    public void setKoordinatX(int koordinatX) {
        this.koordinatX = koordinatX;
    }

    public int getKoordinatY() {
        return koordinatY;
    }

    public void setKoordinatY(int koordinatY) {
        this.koordinatY = koordinatY;
    }

    public int getSandikBoy() {
        return sandikBoy;
    }

    public void setSandikBoy(int sandikBoy) {
        this.sandikBoy = sandikBoy;
    }

    public int getSandikGenislik() {
        return sandikGenislik;
    }

    public void setSandikGenislik(int sandikGenislik) {
        this.sandikGenislik = sandikGenislik;
    }
}
*/
