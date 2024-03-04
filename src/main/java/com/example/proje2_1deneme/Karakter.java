package com.example.proje2_1deneme;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.proje2_1deneme.Main.KARE_BOYUTU;
import static com.example.proje2_1deneme.Main.KARE_GENISLIK;

public class Karakter {
    private String imagePath;
    private String karakterId;
    private String ad;
    private int x;
    private int y;

    public Karakter(String imagePath,String karakterId, String ad, int x, int y) {
        this.imagePath = imagePath;
        this.karakterId = karakterId;
        this.ad = ad;
        this.x = x;
        this.y = y;
    }

    static Karakter karakter = new Karakter("file:///C:\\Users\\musta\\Desktop\\Engeller/", "Mario.png", "Mario", 0, 0);
    // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra karakter oluşmamasını sağlıyor
    static ImageView karakterImageView = new ImageView();

    public static void karakterOlustur(Group root){

        // Önceki karakteri temizle
        root.getChildren().remove(karakterImageView);

        Random random = new Random();
        karakter.setX((int) (Math.random() * KARE_GENISLIK));
        karakter.setY((int) (Math.random() * KARE_GENISLIK));
        // çakışma var mı kontrol et !!
        for(int i=0; i<SabitEngeller.sabitEngellerArrayList.size(); i++){
            if(karakter.getX() == SabitEngeller.sabitEngellerArrayList.get(i).getEngelX()){
                karakterOlustur(root);
            }else if(true){
                for(int j=0; j<DinamikEngeller.hareketliEngelArrayList.size(); j++){
                    if(karakter.getX() == DinamikEngeller.hareketliEngelArrayList.get(j).getEngelX()){
                        karakterOlustur(root);
                    }else{
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        // Önceki ImageView'i güncelle
        Image imageKarakter = new Image(karakter.getImagePath() + karakter.getKarakterId());
        karakterImageView.setImage(imageKarakter);
        karakterImageView.setId(karakter.getKarakterId());

        karakterImageView.setFitWidth(KARE_BOYUTU * 3);
        karakterImageView.setFitHeight(KARE_BOYUTU * 3);
        karakterImageView.setX(karakter.getX() * KARE_BOYUTU);
        karakterImageView.setY(karakter.getY() * KARE_BOYUTU);

        root.getChildren().add(karakterImageView);
    }



    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getKarakterId() {
        return karakterId;
    }

    public void setKarakterId(String karakterId) {
        this.karakterId = karakterId;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Karakter getKarakter() {
        return karakter;
    }

    public static void setKarakter(Karakter karakter) {
        Karakter.karakter = karakter;
    }
}
