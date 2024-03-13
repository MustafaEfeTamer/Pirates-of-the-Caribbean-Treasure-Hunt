package com.example.proje2_1deneme;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.proje2_1deneme.Main.*;

public class Karakter {
    private String imagePath;
    private String karakterId;
    private String ad;
    private int karakterGenislik;
    private int karakterBoy;
    private int x;
    private int y;
    private int startX;
    private int startY;


    public Karakter(String imagePath, String karakterId, String ad, int karakterGenislik, int karakterBoy, int x, int y, int startX, int startY) {
        this.imagePath = imagePath;
        this.karakterId = karakterId;
        this.ad = ad;
        this.karakterGenislik = karakterGenislik;
        this.karakterBoy = karakterBoy;
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
    }

    public Karakter(){

    }


    static Karakter karakter = new Karakter("file:///C:\\Users\\musta\\Desktop\\Engeller/", "JackSparrow.png", "JackSparrow",1,1,0,0, 0, 0);
    static ImageView karakterImageView = new ImageView();

    public static void karakterOlustur(Group root){

        // Önceki karakteri temizle
        root.getChildren().remove(karakterImageView);

        Random random = new Random();
        int engelX, engelY;

        engelX = (int) (Math.random() * KARE_YUKSEKLIK);
        engelY = (int) (Math.random() * KARE_GENISLIK);

        // karakter - sabit engel çakışma kontrolü
        boolean overlap = false;
        /*for (SabitEngeller existingEngel : SabitEngeller.sabitEngellerArrayList) {
            if (Math.abs(existingEngel.getEngelX() - engelX) < 5 && Math.abs(existingEngel.getEngelY() - engelY) < 5) { // buradaki 5 diğer sabit nesnelerle arasındaki mesafe. Bu değer ne kadar fazla olursa engeller birbirinden o kadar uzakta olurlar.
                overlap = true;
                break;
            }
        }

        // karakter - hareketli engel çakışma kontrolü
        for (DinamikEngeller existingEngel : DinamikEngeller.hareketliEngelArrayList) {
            if (Math.abs(existingEngel.getEngelX() - engelX) < 5 && Math.abs(existingEngel.getEngelY() - engelY) < 5) { // buradaki 5 diğer hareketli nesnelerle arasındaki mesafe. Bu değer ne kadar fazla olursa engeller birbirinden o kadar uzakta olurlar.
                overlap = true;
                break;
            }
        }

        // karakter - hazine çakışma kontrolü
        for (Hazine existingEngel : Hazine.cisimlerArrayList) {
            if (Math.abs(existingEngel.getKoordinatX() - engelX) < 5 && Math.abs(existingEngel.getKoordinatY() - engelY) < 5) { // buradaki 5 hazinelerle arasındaki mesafe. Bu değer ne kadar fazla olursa engeller birbirinden o kadar uzakta olurlar.
                overlap = true;
                break;
            }
        }*/

        // Çakışma yoksa engelin x, y sini kur
        if (!overlap) {
            karakter.setX(engelX);
            karakter.setY(engelY);

            karakter.setStartX(karakter.getX());
            karakter.setStartY(karakter.getY());

        } else {
            karakterOlustur(root);
        }

        // biraz bekle sonra sil
        //karakter.setX((int) (Math.random() * KARE_GENISLIK - 2));
        //karakter.setY((int) (Math.random() * KARE_GENISLIK - 2));

        System.out.println(karakter.getX() + ", " + karakter.getY());  // denemek için

        // Önceki ImageView'i güncelle
        Image imageKarakter = new Image(karakter.getImagePath() + karakter.getKarakterId());
        karakterImageView.setImage(imageKarakter);
        karakterImageView.setId(karakter.getKarakterId());

        karakterImageView.setFitWidth(KARE_BOYUTU * karakter.getKarakterGenislik());
        karakterImageView.setFitHeight(KARE_BOYUTU * karakter.getKarakterBoy());
        karakterImageView.setX(karakter.getX() * KARE_BOYUTU);
        karakterImageView.setY(karakter.getY() * KARE_BOYUTU);

        root.getChildren().add(karakterImageView);
    }

    public static void karakterHareket(Group root) {
        Uygulama uygulama = new Uygulama();
        AtomicInteger index = new AtomicInteger(0);
        Timer timer = new Timer();
        int startX, startY, endX, endY, currentX, currentY;

        for(int i = 0; i < 20; i++) {
            // Başlangıç ve bitiş noktaları
            if (i == 0) {
                startX = karakter.getX();
                startY = karakter.getY();
                Koordinat.koordinatlar.add(new Koordinat(startX, startY));

                endX = uygulama.sandikSiralamasi.get(0).getxKoordinati();
                endY = uygulama.sandikSiralamasi.get(0).getyKoordinati();
            } else {
                startX = uygulama.sandikSiralamasi.get(i - 1).getxKoordinati();
                startY = uygulama.sandikSiralamasi.get(i - 1).getyKoordinati();

                endX = uygulama.sandikSiralamasi.get(i).getxKoordinati();
                endY = uygulama.sandikSiralamasi.get(i).getyKoordinati();
            }


            // X ve Y koordinatlarına göre adım adım hareket et
            currentX = startX;
            currentY = startY;

            while (currentX != endX || currentY != endY) {
                // Hedefe ulaşıncaya kadar x ve y koordinatlarını güncelle
                if (currentX != endX) {
                    if (currentX < endX) {
                        currentX++;
                    } else if (currentX > endX) {
                        currentX--;
                    }
                } else {
                    if (currentY < endY) {
                        currentY++;
                    } else if (currentY > endY) {
                        currentY--;
                    }
                }

                // Güncellenmiş koordinatları listeye ekle
                Koordinat.koordinatlar.add(new Koordinat(currentX, currentY));
            }
        }

            // karakteri koordinatlar arraylistine göre hareket ettirme
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        Koordinat kordinat = Koordinat.koordinatlar.get(index.get());

                        karakterImageView.setX(kordinat.getX() * KARE_BOYUTU);
                        karakterImageView.setY(kordinat.getY() * KARE_BOYUTU);

                        index.incrementAndGet();

                        if (index.get() >= Koordinat.koordinatlar.size()) {
                            timer.cancel();
                        }
                    });
                }
            }, 0, 500); // 100 milisaniye (0.1 saniye) aralıklarla hareket et


        // bir sonraki yeni harita oluştururken sandikSiralamasi arraylistinin içindeki mevcut şeylere yenileri eklenmesin diye resetliyoruz.
        Uygulama.sandikSiralamasi.clear();
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

    public int getKarakterGenislik() {
        return karakterGenislik;
    }

    public void setKarakterGenislik(int karakterGenislik) {
        this.karakterGenislik = karakterGenislik;
    }

    public int getKarakterBoy() {
        return karakterBoy;
    }

    public void setKarakterBoy(int karakterBoy) {
        this.karakterBoy = karakterBoy;
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

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
