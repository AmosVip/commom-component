package com.jindashi.ptm.treemap;

public class Rect {
    public double x, y, w, h;

    public Rect() {
        this(0, 0, 1, 1);
    }

    public Rect(Rect r) {
        setRect(r.x, r.y, r.w, r.h);
    }

    public Rect(double x, double y, double w, double h) {
        setRect(x, y, w, h);
    }

    public void setRect(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rect copy() {
        return new Rect(x, y, w, h);
    }

    public String toString() {
        return "Rect: x=" + x + ", y=" + y + ", w=" + w + ", h=" + h;
    }

}
