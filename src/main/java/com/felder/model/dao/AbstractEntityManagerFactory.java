package com.felder.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class AbstractEntityManagerFactory {
    
    protected final String UNIT_MYSQL_SCANNER1 = "mysql_scanner1";

    protected EntityManagerFactory emf;
    protected EntityManager em;

    protected void begin(String unit) {
        this.emf = Persistence.createEntityManagerFactory(unit);
        this.em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    protected void commit() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
