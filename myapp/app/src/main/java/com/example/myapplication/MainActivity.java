package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createNotificationChannel();
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sharedPreferences.getLong("Last_sent",calendar.getTimeInMillis()));

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        setContentView(R.layout.activity_main);
        TextView tv1 = findViewById(R.id.lastSent);
        tv1.setText(mDay+"-"+mMonth+"-"+mYear +" : "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
        TextView tv2 = findViewById(R.id.lastActive);
        tv2.setText(String.valueOf(sharedPreferences.getFloat("Last_data_sent",5.0f)));


        Intent intent = new Intent(MainActivity.this, SensorService.class);

        dbHelper = new DatabaseHelper(this);



//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
//                20,0 , 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime()+10*1000,
                5*1000, pi);





    }
    public void onClick (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Curefit";
            String description = "";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channelId), name, importance);
            //channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
