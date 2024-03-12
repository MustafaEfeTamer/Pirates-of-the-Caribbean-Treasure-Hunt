package com.example.proje2_1deneme;

import java.util.ArrayList;

public class Koordinat {

    private int x;
    private int y;

    static ArrayList<Koordinat> koordinatlar = new ArrayList<>();

    public Koordinat(int x, int y) {
        this.x = x;
        this.y = y;
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
