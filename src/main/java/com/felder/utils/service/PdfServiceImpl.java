package com.felder.utils.service;

import com.felder.model.entity.Pallet;
import com.felder.utils.IPdf;
import com.felder.utils.PdfImplBoxes;
import com.felder.utils.PdfImplPallets;
import java.util.List;

public class PdfServiceImpl implements IPdfService<List<Pallet>, String> {

    IPdf pdfPallets = new PdfImplPallets();

    IPdf pdfBoxes = new PdfImplBoxes();

    @Override
    public void makeFromPallets(List<Pallet> pallets, String path) throws Exception {
        this.pdfPallets.make(pallets, path);
    }

    @Override
    public void makeFromBoxes(List<Pallet> pallets, String path) throws Exception {
        this.pdfBoxes.make(pallets, path);
    }

}
