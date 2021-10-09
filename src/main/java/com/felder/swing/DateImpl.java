package com.felder.swing;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateImpl implements IDate {
    @Override
    public Timestamp formatingDate(Date date, Integer hour, Integer minute, Integer second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
