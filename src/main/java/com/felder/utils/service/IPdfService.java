package com.felder.utils.service;

import com.felder.model.entity.Pallet;
import java.util.List;

public interface IPdfService<O, S> {

    public void makeFromPallets(O pallets, S path) throws Exception;

    public void makeFromBoxes(O pallets, S path) throws Exception;
}
