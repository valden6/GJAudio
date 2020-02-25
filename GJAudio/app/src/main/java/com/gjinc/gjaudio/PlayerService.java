package com.gjinc.gjaudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import java.io.IOException;

public  class  PlayerService  extends Service implements  MediaPlayer.OnErrorListener,  MediaPlayer.OnPreparedListener  {

    private  final Binder binder  =  new  PlayerBinder();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public  int  onStartCommand(Intent intent, int  flags, int  startId)  {
        return  START_STICKY_COMPATIBILITY;
    }

    public  void  play(String  path)  throws IOException {
        mediaPlayer.setDataSource(path);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();
    }

    public void play(){
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public  void  stop()  {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent  intent)  {
        return  binder;
    }

    public  void  onPrepared(MediaPlayer  player)  {
        player.start();
    }

    @Override
    public  boolean  onError(MediaPlayer  mp,  int  what,  int  extra)  {
        return  false;
    }

    public  class  PlayerBinder  extends  Binder  {
        public  PlayerService  getService()  {
            return  PlayerService.this;
        }
    }
}