package com.example.proje2_1deneme;

import java.util.ArrayList;
import static com.example.proje2_1deneme.Hazine.cisimlerArrayList;

public class Uygulama {
    ArrayList<Lokasyon> mesafeArraylisti = new ArrayList();
    static ArrayList<Lokasyon> sandikSiralamasi = new ArrayList<>();


    public void enKisaYolBul(){
        // karakter ile hazineler arasındaki mesafeleri bulup mesafe Arraylistinin içine atıyoruz
        for(int i = 0; i < cisimlerArrayList.size(); i++){
           double mesafe =  Math.pow((cisimlerArrayList.get(i).getKoordinatX() - Karakter.karakter.getX()), 2) + Math.pow((cisimlerArrayList.get(i).getKoordinatY() - Karakter.karakter.getY()), 2) ;
            mesafeArraylisti.add(new Lokasyon(cisimlerArrayList.get(i).getKoordinatX(), cisimlerArrayList.get(i).getKoordinatY(), mesafe, i, cisimlerArrayList.get(i).getSandikTur()));
        }
        // karaktere en yakın hazineyi ve indeksini bul
        double minimumMesafe = mesafeArraylisti.get(0).getMesafe();
        int minimumIndex = 0;
        for(int i = 0; i < mesafeArraylisti.size(); i++){
            if(mesafeArraylisti.get(i).getMesafe() < minimumMesafe){
                minimumMesafe = mesafeArraylisti.get(i).getMesafe();
                minimumIndex = i;
            }
        }
        // hazineye gittikten sonra onun x ve y sini karaktere kur ve güncelle
        Karakter.karakter.setX(mesafeArraylisti.get(minimumIndex).getxKoordinati());
        Karakter.karakter.setY(mesafeArraylisti.get(minimumIndex).getyKoordinati());
        // sandikSiralamasi arraylistine hazineyi ve bilgilerini ekle
        sandikSiralamasi.add(new Lokasyon(mesafeArraylisti.get(minimumIndex).getxKoordinati(), mesafeArraylisti.get(minimumIndex).getyKoordinati(), mesafeArraylisti.get(minimumIndex).getMesafe(), mesafeArraylisti.get(minimumIndex).getIndex(), mesafeArraylisti.get(minimumIndex).getSandikTurr()));
        // son olarak cisimlerArrayListinden sandikSiralamasi na eklediğimiz elemanı kaldır ve mesafeArraylistinin içini özyineleme için boşalt.
        cisimlerArrayList.remove(mesafeArraylisti.get(minimumIndex).getIndex());
        mesafeArraylisti.clear();

        if(cisimlerArrayList.size() > 0){
            enKisaYolBul();
        }
    }

    public void sandikSiralamasi() {
        for (int i = 0; i < sandikSiralamasi.size(); i++) {
            System.out.println(sandikSiralamasi.get(i).getSandikTurr() + " sandık toplandı! (" + sandikSiralamasi.get(i).getxKoordinati() + ", " + sandikSiralamasi.get(i).getyKoordinati() + ") konumunda bulundu.");
        }
    }
}
