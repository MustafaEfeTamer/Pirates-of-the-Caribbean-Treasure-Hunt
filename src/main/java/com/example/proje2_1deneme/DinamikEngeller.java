package com.example.proje2_1deneme;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.*;

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
            "Kuş.png", 0, 0, 2, 2, "Y", 1);
    static DinamikEngeller ari = new DinamikEngeller("file:///C:\\Users\\musta\\Desktop\\Engeller/",
            "Arı.png", 0, 0, 2, 2, "X", 1);

    static DinamikEngeller[] dinamikEngeller = {kus, ari};
    static List<DinamikEngeller> hareketliEngelArrayList = new ArrayList<>();
    static List<ImageView> hareketliEngelImageViews = new ArrayList<>();


    public static void hareketliEngelOlustur(Group root) throws CloneNotSupportedException {

        // program her çalıştırıldığında bu method çağırılcak ve aşağıdaki kod her seferinde extra dinamik engel oluşmamasını sağlıyor
        root.getChildren().removeAll(hareketliEngelImageViews);
        hareketliEngelImageViews.clear();
        hareketliEngelArrayList.clear();

        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int a = random.nextInt(dinamikEngeller.length);
            DinamikEngeller yerlestirilecekHareketliEngel = (DinamikEngeller) dinamikEngeller[a].clone();
            int engelX = (int) (Math.random() * KARE_YUKSEKLIK);
            int engelY = (int) (Math.random() * KARE_GENISLIK);


            // Çakışma kontrolü
            boolean overlap = false;
            for (DinamikEngeller existingEngel : hareketliEngelArrayList) {
                if (Math.abs(existingEngel.getEngelX() - engelX) < 5 && Math.abs(existingEngel.getEngelY() - engelY) < 5) { // buradaki 5 diğer dinamik nesnelerle arasındaki mesafe. Bu değer ne kadar fazla olursa engeller birbirinden o kadar uzakta olurlar.
                    overlap = true;
                    break;
                }
            }

            for (SabitEngeller existingEngel : SabitEngeller.sabitEngellerArrayList) {
                if (Math.abs(existingEngel.getEngelX() - engelX) < 5 && Math.abs(existingEngel.getEngelY() - engelY) < 5) { // buradaki 5 diğer sabit nesnelerle arasındaki mesafe. Bu değer ne kadar fazla olursa engeller birbirinden o kadar uzakta olurlar.
                    overlap = true;
                    break;
                }
            }

            // Çakışma yoksa engeli ekle
            if (!overlap) {
                // engelin x, y sini kur
                yerlestirilecekHareketliEngel.setEngelX(engelX);
                yerlestirilecekHareketliEngel.setEngelY(engelY);
                // engeli ekle
                hareketliEngelArrayList.add(yerlestirilecekHareketliEngel);
            } else {
                i--; // Çakışma varsa i'yi azalt
            }
        }

        for (DinamikEngeller hareketliEngel : hareketliEngelArrayList) {
            Image imageHareketliEngel = new Image(hareketliEngel.getImagePath() + hareketliEngel.getAd());
            ImageView imageView = new ImageView(imageHareketliEngel);
            imageView.setId(hareketliEngel.getAd());

            imageView.setFitWidth(KARE_BOYUTU * hareketliEngel.getEngelGenislik());
            imageView.setFitHeight(KARE_BOYUTU * hareketliEngel.getEngelBoy());
            imageView.setX(hareketliEngel.getEngelX() * KARE_BOYUTU - (imageView.getFitWidth() / 2));
            imageView.setY(hareketliEngel.getEngelY() * KARE_BOYUTU - (imageView.getFitHeight() / 2));

            hareketliEngelImageViews.add(imageView);
            root.getChildren().add(imageView);
        }
    }


    public static void hareketEttir() {

        // Hareketin hızını ve toplam hareket mesafelerini alıyoruz.
        final double[] hareketMesafesi = {0.5};//1 br hareket ediyor her seferinde (hızı)
        final double[] toplamHareket = {0};

        final double[] hareketMesafesi1 = {0.5};//1 br hareket ediyor her seferinde (hızı)
        final double[] toplamHareket1 = {0};

        // harekelti engellerin hareketi burada sağlanıyor
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(16), // Frame Time (60 FPS için 16ms)
                event -> {
                    for (int i = 0; i < hareketliEngelArrayList.size(); i++) {
                        // Kuş için
                        if(hareketliEngelArrayList.get(i).getAd().equals("Kuş.png")){
                            // kuşun yolunu kırmızıya boyamak için
                            for(int j=0; j<5; j++){
                                drawDynamicObstaclePath(gc, hareketliEngelArrayList.get(i).getEngelX(), hareketliEngelArrayList.get(i).getEngelY() + j);
                                drawDynamicObstaclePath(gc, hareketliEngelArrayList.get(i).getEngelX() - 1, hareketliEngelArrayList.get(i).getEngelY() + j);
                            }
                            // Y pozisyonunu sürekli güncelle
                            hareketliEngelImageViews.get(i).setTranslateY(hareketliEngelImageViews.get(i).getTranslateY() + hareketMesafesi[0] );//sürekli 1 br translate ediyor
                            // Kuş için toplam hareket kuşun hareket edeceği boyutu geçtiyse yönü tersine çevir
                            if (Math.abs(toplamHareket[0]) >= 9 * KARE_BOYUTU) {
                                hareketMesafesi[0] *= -1;//- yönde hareket etmeye başlıyo
                                toplamHareket[0] = 0;
                            }
                            // Toplam hareketi güncelle
                            toplamHareket[0] += hareketMesafesi[0];
                        }

                        // Arı için
                        else if(hareketliEngelArrayList.get(i).getAd().equals("Arı.png")){
                            // arının yolunu kırmızıya boyamak için
                            for(int j = 0; j < 2; j++){
                                drawDynamicObstaclePath(gc, hareketliEngelArrayList.get(i).getEngelX(), hareketliEngelArrayList.get(i).getEngelY() - j);
                                drawDynamicObstaclePath(gc, hareketliEngelArrayList.get(i).getEngelX() + 1, hareketliEngelArrayList.get(i).getEngelY() - j);
                                drawDynamicObstaclePath(gc, hareketliEngelArrayList.get(i).getEngelX() + 2, hareketliEngelArrayList.get(i).getEngelY() - j);
                            }

                            hareketliEngelImageViews.get(i).setTranslateX(hareketliEngelImageViews.get(i).getTranslateX()+hareketMesafesi1[0]);
                            if(Math.abs(toplamHareket1[0]) >= 5 * KARE_BOYUTU){
                                hareketMesafesi1[0] *=-1;
                                toplamHareket1[0]=0;
                            }
                            // Toplam hareketi güncelle
                            toplamHareket1[0] += hareketMesafesi1[0];
                        }
                    }
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE); // Sonsuz döngüye sok
        // Timeline'ı başlat
        timeline.play();
    }
}
