package com.example.proje2_1deneme;

import java.util.ArrayList;

public class Karakter {
    public static String karakterResim = "Mario.png";
    int karakterId;
    String ad;
    private int x;
    private int y;

    public Karakter(int karakterId, String ad, int x, int y) {
        this.karakterId = karakterId;
        this.ad = ad;
        this.x = x;
        this.y = y;
    }

    public int getKarakterId() {
        return karakterId;
    }

    public void setKarakterId(int karakterId) {
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
}
