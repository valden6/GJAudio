package com.gjinc.gjaudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.gjinc.gjaudio.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PlayerService playerService;
    private boolean isMusicPlay;
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        Intent intentPlayer = new Intent(getApplicationContext(), PlayerService.class);
        ServiceConnection serviceConnectionPlayer = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder binder) {
                Log.d("ServiceConnection", "connected");
                playerService = ((PlayerService.PlayerBinder) binder).getService();
            }
            //binder comes from server to communicate with method's of

            public void onServiceDisconnected(ComponentName className) {
                Log.d("ServiceConnection", "disconnected");
                playerService = null;
            }
        };

        bindService(intentPlayer, serviceConnectionPlayer, Context.BIND_AUTO_CREATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
        } else {
            //readDataExternal();

            MyListener listener = new MyListener() {
                @Override
                public void onDoneSomething(AudioFile file) {
                    System.out.println("clicked on : " + file.getTitle());
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    final AudioFileManagerFragment fragment = new AudioFileManagerFragment();
                    fragment.setAudioFile(file);
                    playerService.stop();
                    try {
                        playerService.play(file.getFilePath());
                        isMusicPlay = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fragment.setPlayButtonListener(new MyPlayButtonListener() {
                        @Override
                        public void onDoneSomething(AudioFile file) {
                            System.out.println("clicked on play/pause button");
                            if (isMusicPlay) {
                                playerService.pause();
                                isMusicPlay = false;
                                fragment.binding.buttonPlay.setBackgroundResource(R.drawable.play);
                            }
                            else {
                                playerService.play();
                                isMusicPlay = true;
                                fragment.binding.buttonPlay.setBackgroundResource(R.drawable.pause);
                            }

                        }
                    });

                    fragment.setNextButtonListener(new MyNextButtonListener() {
                        @Override
                        public void onDoneSomething(AudioFile file) {
                            System.out.println("clicked on next button");
                            // TODO Changer de musique avec la suivante
                        }
                    });

                    fragment.setPreviousButtonListener(new MyPreviousButtonListener() {
                        @Override
                        public void onDoneSomething(AudioFile file) {
                            System.out.println("clicked on previous button");
                            // TODO Changer de musique avec la précédente
                        }
                    });

                    transaction.add(R.id.fragment_container, fragment);
                    transaction.commit();
                }
            };

            showStartup(listener);
        }
    }

    public  void  showStartup(MyListener listener)  {
        FragmentManager manager  =  getSupportFragmentManager();
        FragmentTransaction transaction  =  manager.beginTransaction();
        AudioFileListFragment  fragment  =  new  AudioFileListFragment();
        fragment.setListener(listener);

        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
}
