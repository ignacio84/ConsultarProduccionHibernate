package com.felder.model.service;

import com.felder.model.dao.IItemDao;
import com.felder.model.dao.ItemDaoImpl;
import com.felder.model.entity.Item;
import java.util.List;

public class ItemServiceImpl implements IItemService {

    private IItemDao itemDao = new ItemDaoImpl();

    @Override
    public List<Item> findAll() {
        return this.itemDao.findAll();
    }
}
