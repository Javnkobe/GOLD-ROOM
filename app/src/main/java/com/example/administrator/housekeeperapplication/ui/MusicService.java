package com.example.administrator.housekeeperapplication.ui;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AssetManager assetManager = getAssets(); //用于获取 Asset文件资源文件
        try {
            AssetFileDescriptor fileDescriptor =
                    assetManager.openFd("city_of_sky.mp3");//加载音乐资源
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            mediaPlayer.prepare();//准备音乐
            mediaPlayer.start();//播放音乐

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }
    public void onDestroy() {
        mediaPlayer.stop();//停止音乐
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
