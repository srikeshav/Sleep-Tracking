package com.example.myapplication;

import android.net.Uri;

public class SensorDataContract {
    public static final String CONTENT_AUTHORITY = "fit.cure.android.provider";
    public static final String BASE_PATH = "sensorData";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" +
            CONTENT_AUTHORITY + "/" + BASE_PATH);

    public static abstract class AccelerometerReadings {
        public static final String TABLE_NAME = "AccelerometerData";
        public static final String TIMESTAMP = "CURTIME";
        public static final String NUMBER_EVENTS = "NUMBER_EVENTS";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("+TIMESTAMP+" LONG PRIMARY KEY, "+ NUMBER_EVENTS + " INTEGER)";

        public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;
    }
}
