package com.felder.swing;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public interface ITable {
 
    public void addDataToDefaultTableModel(DefaultTableModel model, List<Object[]> list);
    
    public void removedDataToDefaultTableModel(DefaultTableModel model);
    

}
