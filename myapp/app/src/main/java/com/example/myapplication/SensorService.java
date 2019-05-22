package com.example.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;


import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class SensorService extends Service implements SensorEventListener {
    private BroadcastReceiver mReceiver = null;
    private SensorManager sensorManager;
    private float prevValues[] = new float[3];
    private static final int THRESHOLD_VALUE = 2;



    public SensorService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
    }
    public void onCreate() {
        super.onCreate();
        Log.d("created","service");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null)
        {

            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification =
                new NotificationCompat.Builder(this, getString(R.string.channelId))
                        .setContentTitle("CureFit")
                        .setContentText("Foreground process running")
                        .setSmallIcon(R.drawable.curefit)
                        .setContentIntent(pendingIntent)
                        .build();
            startForeground(1, notification);
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            if(powerManager.isScreenOn())
                stopSelf();









            initializeSensorManager();
            final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            mReceiver = new ScreenReceiver();
            registerReceiver(mReceiver, filter);

            Log.d("Started ", "foreground ");
            Calendar calendar = Calendar.getInstance();





        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void initializeSensorManager() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
                //Log.d("Sensor","changed");
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER

                    && (prevValues == null
                            || MathUtil.vectorDistance(event.values, prevValues) > 0.2)){
                    Log.d("", "LOG SENSOR CHANGE");

                    prevValues[0] = event.values[0];
                    prevValues[1] = event.values[1];
                    prevValues[2] = event.values[2];
                    Calendar calendar = Calendar.getInstance();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putLong("Last_sent",calendar.getTimeInMillis());
                    editor.putFloat("Last_data_sent",(float) MathUtil.magnitude(prevValues));
                    editor.apply();
                    Log.d("accelerometer value:",String.valueOf(MathUtil.magnitude(prevValues)));
                }

    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1){

    }
    @Override
    public void onDestroy(){
        Log.d("killed","Service");
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            sensorManager = null;
        }
        unregisterReceiver(mReceiver);

    }





}
