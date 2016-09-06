package com.example.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int nummassage = 0;
    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    public void update(View view){

    }
    public void notifyOne(View view){
        android.support.v4.app.NotificationCompat.Builder  builder =
                new android.support.v4.app.NotificationCompat.Builder(this);
        //通知三要素set.ContentTitle setContentText setSmallIcon
        builder.setTicker("新消息")
                .setContentTitle("腾讯奥运新闻")
                .setContentText("又一奥运健儿为国争光")
                .setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis())
                .setAutoCancel(true);
        //测试震动 声音 闪光灯
        builder.setDefaults(Notification.DEFAULT_VIBRATE
                |Notification.DEFAULT_SOUND
                |Notification.DEFAULT_LIGHTS);
        builder.setNumber(++nummassage);
        Notification notification = builder.build();
        int notifyId = 110;
        manager.notify(notifyId,notification);
    }
    public void notifyTwo(View view){
        android.support.v4.app.NotificationCompat.Builder  builder =
                new android.support.v4.app.NotificationCompat.Builder(this);
        builder.setTicker("新消息")
                .setContentTitle("未接电话")
                .setContentText("18223456789")
                .setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("电话号码"+"18223456789"));
        PendingIntent pi = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        );
        builder.setContentIntent(pi);
        Notification notification = builder.build();
        int notifyId = 110;
        manager.notify(notifyId,notification);
    }
    public void notifyThree(View view){

    }
}
