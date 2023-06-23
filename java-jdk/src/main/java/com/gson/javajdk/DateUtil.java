package com.gson.javajdk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }
}
