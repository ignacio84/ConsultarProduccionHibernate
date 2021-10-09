package com.felder.swing;

import java.sql.Timestamp;
import java.util.Date;

public interface IDate {
    public Timestamp formatingDate(Date date, Integer hour, Integer minute, Integer second);
}
