package com.felder.swing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateImpl implements IFormatDate {

    @Override
    public Date switchFormat(String date, String formatOrigin, String formatNew) throws Exception {
        Date dateFec = (Date) new SimpleDateFormat(formatOrigin).parse(date);
        return(Date) new SimpleDateFormat(formatNew).parse(new SimpleDateFormat(formatNew).format(dateFec));
    }

}
