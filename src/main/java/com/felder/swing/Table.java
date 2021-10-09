package com.felder.swing;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Table implements ITable {

    @Override
    public void addDataToDefaultTableModel(DefaultTableModel model, List<Object[]> list) {
        this.removedDataToDefaultTableModel(model);
        list.stream().forEach((data) -> {
            model.addRow(data);
        }
        );
    }

    @Override
    public void removedDataToDefaultTableModel(DefaultTableModel model) {
        model.setRowCount(0);
    }

}
