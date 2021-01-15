package com.felder.model.service;

import com.felder.model.pojo.Producto;
import java.util.List;

public interface IProductService {

    public List<Producto> getAll() throws Exception;

}
