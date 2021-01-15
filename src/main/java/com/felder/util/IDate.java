package com.felder.util;

import java.sql.Timestamp;
import java.util.Date;

public interface IDate {
    public Timestamp formatingDate(Date date, Integer hour, Integer minute, Integer second);
}
