package com.example.proje2_1deneme;

public class Lokasyon {
    private int xKoordinati;
    private int yKoordinati;
    private double mesafe;
    private int index;
    private String sandikTurr;

    public Lokasyon(int xKoordinati, int yKoordinati, double mesafe, int index, String sandikTurr) {
        this.xKoordinati = xKoordinati;
        this.yKoordinati = yKoordinati;
        this.mesafe = mesafe;
        this.index = index;
        this.sandikTurr = sandikTurr;
    }


    public int getxKoordinati() {
        return xKoordinati;
    }

    public void setxKoordinati(int xKoordinati) {
        this.xKoordinati = xKoordinati;
    }

    public int getyKoordinati() {
        return yKoordinati;
    }

    public void setyKoordinati(int yKoordinati) {
        this.yKoordinati = yKoordinati;
    }

    public double getMesafe() {
        return mesafe;
    }

    public void setMesafe(double mesafe) {
        this.mesafe = mesafe;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSandikTurr() {
        return sandikTurr;
    }

    public void setSandikTurr(String sandikTurr) {
        this.sandikTurr = sandikTurr;
    }
}
