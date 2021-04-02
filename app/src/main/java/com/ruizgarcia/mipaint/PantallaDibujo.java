package com.ruizgarcia.mipaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Clase que gestiona la actividad principal de la aplicación. En este caso es muy sencilla
public class PantallaDibujo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_dibujo);
    }


}