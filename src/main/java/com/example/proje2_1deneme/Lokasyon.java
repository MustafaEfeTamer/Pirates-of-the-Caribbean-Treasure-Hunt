package com.example.proje2_1deneme;

public class Lokasyon {
    private int cisim;
    private int x;
    private int y;

    public Lokasyon(int cisim, int x, int y) {
        this.cisim = cisim;
        this.x = x;
        this.y = y;
    }

    public int getCisim() {
        return cisim;
    }

    public void setCisim(int cisim) {
        this.cisim = cisim;
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
