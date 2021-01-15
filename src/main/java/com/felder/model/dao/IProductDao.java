
package com.felder.model.dao;

import com.felder.model.pojo.Producto;
import java.util.List;

public interface IProductDao {

    public List<Producto> getAll() throws Exception ;
    
}
