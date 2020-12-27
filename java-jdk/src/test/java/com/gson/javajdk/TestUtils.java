package com.gson.javajdk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        return time;
    }
}
