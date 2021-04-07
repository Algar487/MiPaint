package com.ruizgarcia.mipaint;

import android.graphics.Path;

//Clase POJO con las características del Path
public class FingerPath {

    public int color; //color
    public boolean star; //estrella
    public boolean face; //mi cara
    public int strokeWidth;
    public Path path;

    //constructor
    public FingerPath(int color, boolean star, boolean face, int strokeWidth, Path path) {
        this.color = color;
        this.star = star;
        this.face = face;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}