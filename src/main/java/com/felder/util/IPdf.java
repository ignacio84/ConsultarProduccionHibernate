package com.felder.util;

import com.felder.model.pojo.Produccion;
import java.util.List;

public interface IPdf  {

    public void makeFromProduccion(List<Produccion> listProduccion, String path) throws Exception;
}
