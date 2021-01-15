package com.felder.util;

import com.felder.model.pojo.Produccion;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;

public interface IXlsx {

    public void makeFromProduccion(List<Produccion> listProduccion, String path) throws Exception;


}
