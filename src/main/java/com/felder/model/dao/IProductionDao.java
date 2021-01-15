package com.felder.model.dao;

import com.felder.model.pojo.Produccion;
import java.sql.Timestamp;
import java.util.List;

public interface IProductionDao {

    public List<Produccion> findProductionByDateAndKey(Timestamp from, Timestamp to, String key) throws Exception;

}
