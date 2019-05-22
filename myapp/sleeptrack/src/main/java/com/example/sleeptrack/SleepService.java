package com.example.sleeptrack;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;


import static android.content.Context.ALARM_SERVICE;

public class SleepService {
    public void setAlarm(Context context, String channelId){
        final long SERVICE_WAKE_INTERVALS = 1 * 60 * 1000;
        //createNotificationChannel(context, channelId);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);
        i.putExtra("channelId",channelId);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime()+10*1000,
                SERVICE_WAKE_INTERVALS, pi);


    }
    private void createNotificationChannel(Context context,String channelId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Curefit";
            String description = "";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            NotificationManager notificationManager = ContextCompat.getSystemService(context,NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
