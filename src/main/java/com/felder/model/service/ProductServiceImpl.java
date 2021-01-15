package com.felder.model.service;

import com.felder.model.dao.IProductDao;
import com.felder.model.dao.ProductDaoImpl;
import com.felder.model.pojo.Producto;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private IProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public List<Producto> getAll() throws Exception {
       return this.productDao.getAll();
    }
    
}
