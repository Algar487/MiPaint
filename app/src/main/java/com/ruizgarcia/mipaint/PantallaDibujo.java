package com.ruizgarcia.mipaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

//Clase que gestiona la actividad principal de la aplicación. En este caso es muy sencilla
public class PantallaDibujo extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pantalla_dibujo);
        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void volverInicio(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void limpiarDibujo(View view) {
        paintView.clear();
    }

    public void pincelRojo(View view) {
        paintView.changeColorToRed();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void pincelAzul(View view) {
        paintView.changeColorToBlue();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void pincelNaranja(View view) {
        paintView.changeColorToOrange();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void pincelVerde(View view) {
        paintView.changeColorToGreen();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    public void pincelEstrella(View view) {
        paintView.star();
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);*/
    }

    public void pincelCara(View view) {
        paintView.face();
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);*/
    }




}