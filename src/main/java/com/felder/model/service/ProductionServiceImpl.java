package com.felder.model.service;

import com.felder.model.dao.IProductionDao;
import com.felder.model.dao.ProductionDaoImpl;
import com.felder.model.pojo.Produccion;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ProductionServiceImpl implements IProductionService {

    private IProductionDao productionDao;

    public ProductionServiceImpl() {
        this.productionDao = new ProductionDaoImpl();
    }

    @Override
    public List<Produccion> findProductionByDateAndKey(Timestamp from, Timestamp to, String key) throws Exception {
        return this.productionDao.findProductionByDateAndKey(from, to, key);
    }

}
