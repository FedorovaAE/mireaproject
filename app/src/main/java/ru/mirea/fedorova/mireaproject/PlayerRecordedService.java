package ru.mirea.fedorova.mireaproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;

public class PlayerRecordedService extends Service {
    private MediaPlayer mediaPlayer;

    public PlayerRecordedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "mirea.3gp");
        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
        mediaPlayer.setLooping(true);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}