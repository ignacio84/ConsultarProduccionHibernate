package com.felder.model.service;

import com.felder.model.dao.IPalletDao;
import com.felder.model.dao.PalletDaoImpl;
import com.felder.model.entity.Pallet;
import java.util.List;

public class PalletServiceImpl implements IPalletService {

    private IPalletDao palletDao = new PalletDaoImpl();

    @Override
    public Pallet findById(Pallet pallet) {
        return this.palletDao.findById(pallet);
    }

    @Override
    public List<Pallet> findByBatch(Pallet pallet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pallet> findByDateAndItemAndBatch(Pallet pallet) {
        return this.palletDao.findByDateAndItemAndBatch(pallet);
    }

    @Override
    public List<Pallet> findByItemAndBatch(Pallet pallet) {
        return this.palletDao.findByItemAndBatch(pallet);
    }

    @Override
    public void insertPallet(Pallet pallet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePallet(Pallet pallet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePallet(Pallet pallet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
