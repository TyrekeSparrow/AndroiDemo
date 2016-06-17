package com.example.michael.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michael on 16-6-16.
 */
public class DateUtil {
    private static final String PATTERN = "HH:mm:ss";

    public static final String getDateLabel() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        final Date date = new Date(System.currentTimeMillis());
        final String timeLabel = simpleDateFormat.format(date);
        return timeLabel;
    }
}
