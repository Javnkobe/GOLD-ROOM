package com.example.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BattEryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
            Bundle extras = intent.getExtras();
            int totalBattery = extras.getInt(BatteryManager.EXTRA_SCALE);
            int currentBattery = extras.getInt(BatteryManager.EXTRA_LEVEL);
            int temperature = extras.getInt(BatteryManager.EXTRA_TEMPERATURE);
            Toast.makeText(context, totalBattery+"::"+currentBattery+"::"+temperature, Toast.LENGTH_SHORT).show();
        }
    }
}
