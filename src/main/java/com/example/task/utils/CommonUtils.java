package com.example.task.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommonUtils {

    public static Date parseTimestamp(String timestamp) {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
