package com.ruizgarcia.mipaint;

import android.graphics.Path;

//Clase POJO con las características del Pincel
public class FingerPath {

    public int color; //color
    public boolean star; //estrella
    public boolean face; //mi cara
    public int strokeWidth;
    public Path path;

    //constructor cuyos parámetros son el color, si el pincel es una estrella, si el pincel es mi propia cara, el tamaño del pincel y el trazo (path). 
    public FingerPath(int color, boolean star, boolean face, int strokeWidth, Path path) {
        this.color = color;
        this.star = star;
        this.face = face;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}