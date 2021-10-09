package com.felder.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class CustomRenderForJCombobox extends DefaultListCellRenderer {

    private Integer aling;
    private Color color;

    public CustomRenderForJCombobox(Integer aling, Color color) {
        this.aling = aling;
        this.color = color;
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (index > -1) {
            lbl.setForeground(color);
            lbl.setHorizontalAlignment(aling);
        }
        return lbl;
    }
}
