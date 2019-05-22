package com.example.sleeptrack;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    //@TargetApi(26)
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.throw new UnsupportedOperationException("Not yet implemented");
        Log.d("Alarm","Called");
        Intent intentService = new Intent(context, SensorService.class);


        if(PendingIntent.getBroadcast(context,0,intentService,PendingIntent.FLAG_NO_CREATE)==null) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intentService);
            } else {
                context.startService(intentService);
            }
        }
    }
}
