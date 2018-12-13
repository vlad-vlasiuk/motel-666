package com.galvanize.motel666.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Date getDate(String date) {
        try {
            return new Date(df.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
