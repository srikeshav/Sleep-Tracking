package com.example.sleeptrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            Log.d("Listener:", "screen turned off ");
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            //Intent stopIntent = new Intent(this,SensorService.class);
            Log.d("Listener:","screen turned on");
            context.stopService(new Intent(context, SensorService.class));
            wasScreenOn = true;
        }
    }
}
