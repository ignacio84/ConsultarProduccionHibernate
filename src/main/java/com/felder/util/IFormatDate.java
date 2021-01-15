package com.felder.util;

import java.util.Date;

public interface IFormatDate {

    //METODO CAMBIA FORMATO DE FECHAS   //switchFormat(new Date(), "yyyy-MM-dd", "dd-MM-yyyy")
    public Date switchFormat(String date, String formatOrigin, String formatNew) throws Exception;
    
}
