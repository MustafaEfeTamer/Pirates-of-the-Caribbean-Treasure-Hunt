package com.example.proje2_1deneme;

import java.util.ArrayList;

public class Uygulama {
    Karakter karakter = new Karakter();
    ArrayList<LokasyonParametreler> lokasyonParametrelerArrayList = new ArrayList<>();

    public void enKisaYolBul(){
        for(int i = 0; i < 3; i++){
            if(i == 0){
                int kenar1 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatX() - karakter.getX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatY() - karakter.getY()), 2);
                int kenar3 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i + 2).getKoordinatX() - Lokasyon.cisimlerArrayList.get(i + 1).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i + 2).getKoordinatY() - Lokasyon.cisimlerArrayList.get(i + 1).getKoordinatY()), 2);
                for(int j = 0; j < 2; j++){
                    int kenar2 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(j + 1).getKoordinatX() - Lokasyon.cisimlerArrayList.get(0).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(j + 1).getKoordinatY() - Lokasyon.cisimlerArrayList.get(0).getKoordinatY()), 2);
                    int yolUzunluk = kenar1 + kenar2 + kenar3;

                    if(j == 0){
                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), yolUzunluk, 3));
                    }else{
                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), yolUzunluk, 3));
                    }
                }
            }else if(i == 1){
                int kenar1 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatX() - karakter.getX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatY() - karakter.getY()), 2);
                int kenar3 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i + 1).getKoordinatX() - Lokasyon.cisimlerArrayList.get(i - 1).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i + 1).getKoordinatY() - Lokasyon.cisimlerArrayList.get(i - 1).getKoordinatY()), 2);
                for(int j = 0; j < 2; j++){
                    if(j == 0){
                        int kenar2 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(j).getKoordinatX() - Lokasyon.cisimlerArrayList.get(1).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(j).getKoordinatY() - Lokasyon.cisimlerArrayList.get(1).getKoordinatY()), 2);
                        int yolUzunluk = kenar1 + kenar2 + kenar3;

                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), yolUzunluk, 3));
                    }else{
                        int kenar2 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(j + 1).getKoordinatX() - Lokasyon.cisimlerArrayList.get(1).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(j + 1).getKoordinatY() - Lokasyon.cisimlerArrayList.get(1).getKoordinatY()), 2);
                        int yolUzunluk = kenar1 + kenar2 + kenar3;

                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), yolUzunluk, 3));
                    }
                }
            }else{
                int kenar1 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatX() - karakter.getX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i).getKoordinatY() - karakter.getY()), 2);
                int kenar3 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(i - 1).getKoordinatX() - Lokasyon.cisimlerArrayList.get(i - 2).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(i - 1).getKoordinatY() - Lokasyon.cisimlerArrayList.get(i - 2).getKoordinatY()), 2);
                for(int j = 0; j < 2; j++){
                    int kenar2 = (int)Math.pow((Lokasyon.cisimlerArrayList.get(j).getKoordinatX() - Lokasyon.cisimlerArrayList.get(2).getKoordinatX()), 2) + (int)Math.pow((Lokasyon.cisimlerArrayList.get(j).getKoordinatY() - Lokasyon.cisimlerArrayList.get(2).getKoordinatY()), 2);
                    int yolUzunluk = kenar1 + kenar2 + kenar3;

                    if(j == 0){
                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), yolUzunluk, 3));
                    }else{
                        lokasyonParametrelerArrayList.add(new LokasyonParametreler(Lokasyon.cisimlerArrayList.get(2).getSandikTur(), Lokasyon.cisimlerArrayList.get(2).getKoordinatX(), Lokasyon.cisimlerArrayList.get(2).getKoordinatY(), Lokasyon.cisimlerArrayList.get(1).getSandikTur(), Lokasyon.cisimlerArrayList.get(1).getKoordinatX(), Lokasyon.cisimlerArrayList.get(1).getKoordinatY(), Lokasyon.cisimlerArrayList.get(0).getSandikTur(), Lokasyon.cisimlerArrayList.get(0).getKoordinatX(), Lokasyon.cisimlerArrayList.get(0).getKoordinatY(), yolUzunluk, 3));
                    }
                }
            }
        }
        int minimum = lokasyonParametrelerArrayList.get(0).getYolUzunluk();
        int minimumIndex = 0;
        for(int i = 1; i < lokasyonParametrelerArrayList.size(); i++){
            if(lokasyonParametrelerArrayList.get(i).getYolUzunluk() < minimum){
                minimum = lokasyonParametrelerArrayList.get(i).getYolUzunluk();
                minimumIndex = i;
            }
        }

        for(int i = 0; i < 6; i++){
            System.out.println(lokasyonParametrelerArrayList.get(i).getYolUzunluk());
        }

        System.out.println("En Kısa Yolun Mesafesi : " + lokasyonParametrelerArrayList.get(minimumIndex).getYolUzunluk() + "\nToplam Adım Sayısı : " + lokasyonParametrelerArrayList.get(minimumIndex).getKacAdim());
        System.out.println("1. sandik : " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik1Tur() + " (" + lokasyonParametrelerArrayList.get(minimumIndex).getSandik1X() + ", " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik1Y() + ")");
        System.out.println("2. sandik : " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik2Tur() + " (" + lokasyonParametrelerArrayList.get(minimumIndex).getSandik2X() + ", " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik2Y() + ")");
        System.out.println("3. sandik : " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik3Tur() + " (" + lokasyonParametrelerArrayList.get(minimumIndex).getSandik3X() + ", " + lokasyonParametrelerArrayList.get(minimumIndex).getSandik3Y() + ")");
    }
}
