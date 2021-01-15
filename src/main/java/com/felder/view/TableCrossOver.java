/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felder.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


public class TableCrossOver extends JTable {

    private Color evenBackColor = Color.white;
    private Color evenTextColor = Color.white;
    private Color oddBackColor = Color.white;
    private Color oddTextColor = Color.BLACK;
    private Color rolloverBackColor = Color.orange;
    private Color rolloverTextColor = Color.white;
    private Color selBackColor = Color.gray;
    private Color selTextColor = Color.white;
    private int rolloverRowIndex = -1;
    Font fnt = new Font("Dialog", Font.BOLD, 13);

    //METODO CONSTRUCTOR
    public TableCrossOver(TableModel model, Font fnt) {
        super(model);
        if (fnt != null) {
            this.fnt = fnt;
        }
        RolloverListener listener = new RolloverListener();
        addMouseMotionListener(listener);
        addMouseListener(listener);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        c.setFont(fnt);
        if (isRowSelected(row)) {
            c.setForeground(selTextColor);
            c.setBackground(selBackColor);
//                c.setForeground(getSelectionForeground());
//                c.setBackground(getSelectionBackground());
        } else if (row == rolloverRowIndex) {
            c.setForeground(rolloverTextColor);
            c.setBackground(rolloverBackColor);
        } //            else if (row % 2 == 0) {
        //                c.setForeground(evenTextColor);
        //                c.setBackground(evenBackColor);
        //            }
        else {
            c.setForeground(oddTextColor);
            c.setBackground(oddBackColor);
        }
        return c;
    }

    private class RolloverListener extends MouseInputAdapter {

        public void mouseExited(MouseEvent e) {
            rolloverRowIndex = -1;
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
            int row = rowAtPoint(e.getPoint());
            if (row != rolloverRowIndex) {
                rolloverRowIndex = row;
                repaint();
            }
        }
    }
}

