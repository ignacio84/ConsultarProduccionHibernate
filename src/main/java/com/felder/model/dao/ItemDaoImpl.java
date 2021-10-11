
package com.felder.model.dao;

import com.felder.model.entity.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends AbstractEntityManagerFactory implements IItemDao {

    @Override
    public List<Item> findAll() {
         List<Item> items = new ArrayList<>();
        this.begin(UNIT_MYSQL_SCANNER1);
        items = em.createNamedQuery("Item.findAll").getResultList();;
        this.commit();
        return items;
    }
}
