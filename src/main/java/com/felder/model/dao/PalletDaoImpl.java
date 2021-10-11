package com.felder.model.dao;

import com.felder.model.entity.Pallet;
import java.util.ArrayList;
import java.util.List;

public class PalletDaoImpl extends AbstractEntityManagerFactory implements IPalletDao {

    

    @Override
    public Pallet findById(Pallet pallet) {
        Pallet p;
        this.begin(UNIT_MYSQL_SCANNER1);
        p = em.find(Pallet.class, pallet.getFolio());
        this.commit();
        return p;
    }

    @Override
    public List<Pallet> findByBatch(Pallet pallet) {
        List<Pallet> pallets = new ArrayList<>();
        this.begin(UNIT_MYSQL_SCANNER1);
        pallets = em.createNamedQuery("Pallet.findByBatch").setParameter("lote", pallet.getLote()).getResultList();
        this.commit();
        return pallets;
    }

    @Override
    public List<Pallet> findByDateAndItemAndBatch(Pallet pallet) {
        System.out.println("findByDateAndItemAndBatch " + pallet);
        List<Pallet> pallets = new ArrayList<>();
        this.begin(UNIT_MYSQL_SCANNER1);
        pallets = em.createQuery(" SELECT p FROM Pallet p where p.fecha BETWEEN :from AND :to AND p.codigoProducto LIKE :codigoProducto  AND  p.lote LIKE :lote ")
                .setParameter("codigoProducto", pallet.getCodigoProducto())
                .setParameter("lote", pallet.getLote())
                .setParameter("from", pallet.getFrom())
                .setParameter("to", pallet.getTo())
                .getResultList();
        this.commit();
        return pallets;
    }

    @Override
    public List<Pallet> findByItemAndBatch(Pallet pallet) {
        List<Pallet> pallets = new ArrayList<>();
        this.begin(UNIT_MYSQL_SCANNER1);
        pallets = em.createNamedQuery("Pallet.findByItemAndBatch")
                .setParameter("codigoProducto", pallet.getCodigoProducto())
                .setParameter("lote", pallet.getLote())
                .getResultList();
        this.commit();
        return pallets;
    }

    @Override
    public Pallet insertPallet(Pallet pallet) {
        this.begin(UNIT_MYSQL_SCANNER1);
        em.persist(pallet);
        this.commit();
        return pallet;
    }

    @Override
    public void updatePallet(Pallet pallet) {
        em.merge(pallet);
    }

    @Override
    public void deletePallet(Pallet pallet) {
        em.remove(em.merge(pallet));
    }
}
