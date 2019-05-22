package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MathUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static double vectorDistance(float values1[], float values2[]) {
        if (values1 != null && values2 != null && values1.length == values2.length) {
            float value = 0;
            for (int i = 0; i < values1.length; i++) {
                float diff = values1[i] - values2[i];
                value += (diff * diff);
            }
            return Math.sqrt(value);
        }
        return -1;
    }
    public static double magnitude(float values[]){
        double i = Math.pow(values[0],2)+Math.pow(values[1],2)+Math.pow(values[2],2);
        return Math.sqrt(i);
    }

    /**
     * Utility function for getting the current time
     *
     * @return current date in DEFAULT_DATE_FORMAT
     */
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DEFAULT_DATE_FORMAT, Locale.ENGLISH);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public  static Date getDateObject(long currentMilis){
        Date date = new Date(currentMilis);
        return date;
    }

    public static int getDate(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static int getMonth(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.YEAR);
    }

    public static int getHour(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond(long currentMilis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        return calendar.get(Calendar.SECOND);
    }

    public static long addSeconds(long currentMilis, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMilis);
        calendar.add(Calendar.SECOND,seconds);
        return calendar.getTimeInMillis();
    }


    public static String getFormatedDateFromIndex(int index, String dateFormatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                dateFormatStr, Locale.ENGLISH);
        return dateFormat.format(getDatefromIndex(index));
    }

    public static long getTimestampFromIndex(int dayIndex, int timeIndex){
        Date date = MathUtil.getDatefromIndex(dayIndex);
        int hour = timeIndex / 12;
        int minute = (timeIndex % 12) * 5;
        return MathUtil.getTimeStamp(date, hour + ":" + minute);
    }

    public static Date getDatefromIndex(int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.DAY_OF_MONTH,-index);
        return new Date(calendar.getTimeInMillis());
    }


    public static int diffInDates(Date startDate, Date endDate){
        Calendar sDate = getDatePart(startDate);
        Calendar eDate = getDatePart(endDate);

        int daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static Calendar getDatePart(Date date){
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second
        return cal;                                  // return the date part
    }

    public static int diffInDays(Date startDate, Date endDate){
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
//        different = different % daysInMilli;

        /*long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;*/

        return (int) elapsedDays;

    }

    public static long subtractMinutes(Date today, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(today.getTime());
        calendar.add(Calendar.MINUTE,-minutes);
        return new Date(calendar.getTimeInMillis()).getTime();
    }



    public static long getTimeStamp(Date date, String hhmm) {
        int hour = Integer.valueOf(hhmm.split(":")[0]);
        int minute = Integer.valueOf(hhmm.split(":")[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        return calendar.getTimeInMillis();
    }
}
