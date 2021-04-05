package com.ruizgarcia.mipaint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

//Clase que gestiona la actividad principal de la aplicación. En este caso es muy sencilla
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MusicService.class));
    }

    public void abrirPantallaDibujo(View view) {
        Intent i = new Intent(this, PantallaDibujo.class);
        startActivity(i);
    }

}