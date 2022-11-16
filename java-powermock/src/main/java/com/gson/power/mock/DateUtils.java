package com.gson.power.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        String time = sdf.format(new Date());
        return time;
    }

    private static long getTime(){
        return System.currentTimeMillis();
    }
}
