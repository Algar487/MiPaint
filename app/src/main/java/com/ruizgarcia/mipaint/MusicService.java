package com.ruizgarcia.mipaint;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {


    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.porcelain);
        player.setLooping(true);
        player.start();

    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        player = MediaPlayer.create(this, R.raw.porcelain);
        player.start();
    }

}

