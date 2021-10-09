package com.felder.utils.service;

import com.felder.model.entity.Pallet;
import com.felder.utils.IXlsx;
import com.felder.utils.XlsxImplBoxes;
import com.felder.utils.XlsxImplPallets;
import java.util.List;

public class XlsServiceImpl implements IXlsService {

    IXlsx xlsPallets = new XlsxImplPallets();

    IXlsx xlsBoxes = new XlsxImplBoxes();

    @Override
    public void makeFromPallets(List<Pallet> pallets, String path) throws Exception {
        this.xlsPallets.make(pallets, path);
    }

    @Override
    public void makeFromBoxes(List<Pallet> pallets, String path) throws Exception {
        this.xlsBoxes.make(pallets, path);
    }

}
