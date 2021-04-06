package com.ruizgarcia.mipaint;

import java.util.*;
import android.graphics.*;

// Usamos un objeto para guardar las coordenadas y la información que necesitemos para dibujar
public class Pintura
{
    public int posX;
    public int posY;

    public Pintura(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }
}
