package com.felder.model.service;

import com.felder.model.pojo.Produccion;
import java.sql.Timestamp;
import java.util.List;

public interface IProductionService {

    public List<Produccion> findProductionByDateAndKey(Timestamp from, Timestamp to, String key) throws Exception;

}
