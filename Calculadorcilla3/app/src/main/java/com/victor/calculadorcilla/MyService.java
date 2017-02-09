package com.victor.calculadorcilla;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class MyService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    private MediaPlayer mediaPlayer;
    private int songPosn;
    private final IBinder musicBind=new MusicBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        songPosn=0;
        mediaPlayer=new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.trololo_song);

    }

    public void initMusicPlayer() {
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    public class MusicBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            mediaPlayer.prepare(); // prepare async to not block main thread    }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flags;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    public void playSong() {
        mediaPlayer.prepareAsync();
    }
}