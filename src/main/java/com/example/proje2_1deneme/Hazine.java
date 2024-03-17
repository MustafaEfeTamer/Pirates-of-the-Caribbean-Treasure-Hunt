package com.example.proje2_1deneme;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proje2_1deneme.Main.*;
import static com.example.proje2_1deneme.Main.KARE_BOYUTU;

public class Hazine implements Cloneable{
    private String imagePath;
    private String sandik;
    private int koordinatX;
    private int koordinatY;
    private int sandikBoy;
    private int sandikGenislik;
    private int sandikNumara;
    private String sandikTur;

    public Hazine(String imagePath, String sandik, int koordinatX, int koordinatY, int sandikBoy, int sandikGenislik, int sandikNumar, String sandikTur) {
        this.imagePath = imagePath;
        this.sandik = sandik;
        this.koordinatX = koordinatX;
        this.koordinatY = koordinatY;
        this.sandikBoy = sandikBoy;
        this.sandikGenislik = sandikGenislik;
        this.sandikNumara = sandikNumara;
        this.sandikTur = sandikTur;
    }

    public Hazine(){

    }

    static Hazine altinSandik = new Hazine("file:///C:\\Users\\musta\\Desktop\\Engeller/", "AltınSandık.png", 0, 0, 2, 2, 0, "altın");
    static Hazine gumusSandik = new Hazine("file:///C:\\Users\\musta\\Desktop\\Engeller/", "GümüşSandık.png", 0, 0, 2, 2, 0, "gümüş");
    static Hazine zumrutSandik = new Hazine("file:///C:\\Users\\musta\\Desktop\\Engeller/", "ZümrütSandık.png", 0, 0, 2, 2, 0, "zümrüt");
    static Hazine bakirSandik = new Hazine("file:///C:\\Users\\musta\\Desktop\\Engeller/", "BakırSandık.png", 0, 0, 2, 2, 0, "bakır");

    static Hazine[] cisimler = {altinSandik, gumusSandik, zumrutSandik, bakirSandik};
    static List<Hazine> cisimlerArrayList = new ArrayList<>();
    static List<ImageView> cisimImageViews = new ArrayList<>();

    static List<Koordinat> ImageViewsKoordinatlari = new ArrayList<>();



    public static void cisimOlustur(Group root) throws CloneNotSupportedException {
        // Bir sonraki harita için içini boşalt
        ImageViewsKoordinatlari.clear();

        // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra hazinelerin oluşmamasını sağlıyor
        root.getChildren().removeAll(cisimImageViews);
        cisimImageViews.clear();
        cisimlerArrayList.clear();

        int engelX, engelY;
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int a = random.nextInt(cisimler.length);
            Hazine yerlestirilecekCisim = (Hazine) cisimler[a].clone();
            // sandık numarasını belirle
            yerlestirilecekCisim.setSandikNumara(i + 1);

            engelX = (int) (Math.random() * KARE_YUKSEKLIK);
            engelY = (int) (Math.random() * KARE_GENISLIK);

            // hazine - hazine Çakışma kontrolü
            boolean overlap = false;
            for (Hazine cisim : cisimlerArrayList) {
                if (Math.abs(cisim.getKoordinatX() - engelX) < 2 && Math.abs(cisim.getKoordinatY() - engelY) < 2) {
                    overlap = true;
                    break;
                }
            }
            // hazine - sabit engel çakışma kontrolü
            for (SabitEngeller cisim : SabitEngeller.sabitEngellerArrayList) {
                if (Math.abs(cisim.getEngelX() - engelX) < 6 && Math.abs(cisim.getEngelY() - engelY) < 6) {
                    overlap = true;
                    break;
                }
            }
            // hazine - dinamik engel çakışması kontrolü
            for (DinamikEngeller cisim : DinamikEngeller.hareketliEngelArrayList) {
                if (Math.abs(cisim.getEngelX() - engelX) < 6 && Math.abs(cisim.getEngelY() - engelY) < 6) {
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

        for (Hazine cisim : cisimlerArrayList) {
            Image imageCisim = new Image(cisim.getImagePath() + cisim.getSandik());
            ImageView imageView = new ImageView(imageCisim);
            imageView.setId(cisim.getSandik());

            imageView.setFitWidth(KARE_BOYUTU * cisim.getSandikGenislik());
            imageView.setFitHeight(KARE_BOYUTU * cisim.getSandikBoy());
            imageView.setX(cisim.getKoordinatX() * KARE_BOYUTU - (imageView.getFitWidth() / 2));
            imageView.setY(cisim.getKoordinatY() * KARE_BOYUTU - (imageView.getFitHeight() / 2));
            // cisimlerArrayListinin içindeki daha en kısa yola göre sıralanmamış hazinelerin koordinatlarını teker teker ImageViewsKoordinatlar arraylistine atarız
            ImageViewsKoordinatlari.add(new Koordinat(cisim.getKoordinatX(), cisim.getKoordinatY()));

            cisimImageViews.add(imageView);
            root.getChildren().add(imageView);
        }
        Uygulama uygulama = new Uygulama();
        uygulama.enKisaYolBul();
        uygulama.sandikSiralamasi();
    }


    public String getImagePath() {
        return imagePath;
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

    public int getSandikNumara() {
        return sandikNumara;
    }

    public void setSandikNumara(int sandikNumara) {
        this.sandikNumara = sandikNumara;
    }

    public String getSandikTur() {
        return sandikTur;
    }

    public void setSandikTur(String sandikTur) {
        this.sandikTur = sandikTur;
    }
}
