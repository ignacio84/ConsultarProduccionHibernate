package com.felder.model.dao;

import com.felder.model.entity.Pallet;
import java.util.List;

public interface IPalletDao {

    public Pallet findById(Pallet pallet);
    
    public List<Pallet> findByBatch(Pallet pallet);

    public List<Pallet> findByDateAndItemAndBatch(Pallet pallet);
    
    public List<Pallet> findByItemAndBatch(Pallet pallet);
    
    public Pallet insertPallet(Pallet pallet);
    
    public void updatePallet(Pallet pallet);
    
    public void deletePallet(Pallet pallet);

}
