package com.ruizgarcia.mipaint;

import android.graphics.Path;

public class FingerPath {

    public int color;
    public boolean star;
    public boolean face;
    public int strokeWidth;
    public Path path;

    public FingerPath(int color, boolean star, boolean face, int strokeWidth, Path path) {
        this.color = color;
        this.star = star;
        this.face = face;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}