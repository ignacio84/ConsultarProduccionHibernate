
package com.felder.model.service;

import com.felder.model.entity.Pallet;
import java.util.List;

public interface IPalletService {
    
    public Pallet findById(Pallet pallet);
    
    public List<Pallet> findByBatch(Pallet pallet);

    public List<Pallet> findByDateAndItemAndBatch(Pallet pallet);
    
    public List<Pallet> findByItemAndBatch(Pallet pallet);
    
    public void insertPallet(Pallet pallet);
    
    public void updatePallet(Pallet pallet);
    
    public void deletePallet(Pallet pallet);
    
}
