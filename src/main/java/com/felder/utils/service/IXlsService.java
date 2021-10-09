package com.felder.utils.service;

import com.felder.model.entity.Pallet;
import java.util.List;

public interface IXlsService {

    public void makeFromPallets(List<Pallet> pallets, String path) throws Exception;;

    public void makeFromBoxes(List<Pallet> pallets, String path) throws Exception;;

}
