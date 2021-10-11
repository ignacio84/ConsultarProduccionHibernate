package com.felder.model.dao;

import com.felder.model.entity.Item;
import java.util.List;

public interface IItemDao {

    public List<Item> findAll();

}
