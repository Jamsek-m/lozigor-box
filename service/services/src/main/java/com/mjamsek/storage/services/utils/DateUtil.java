package com.mjamsek.storage.services.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static Date getDateNSecondsFromNow(int seconds) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
    
}
