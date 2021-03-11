package com.felder.view;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ConsultarView extends AbstractJFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel panelTitle = new JPanel();
    private final JPanel panelMain = new JPanel();

    public JComboBox comboProducts = new JComboBox();
    public JButton buttonSearch = new JButton("BUSCAR");
    public JButton buttonExcel;
    public JButton buttonPdf;

    public JLabel labelTotalWeight = new JLabel("0.00");
    public JLabel labelTotalBoxes = new JLabel("0");
    public JLabel labelTotalPallets = new JLabel("0");

    private final JDateChooser dateChooserFrom = new JDateChooser();
    private final JDateChooser dateChooserTo = new JDateChooser();

    private final JLabel labelDateFrom = new JLabel("FECHA DESDE :");
    private final JLabel labelDateTo = new JLabel("FECHA HASTA :");
    private final JLabel labelProducts = new JLabel("PRODUCTO :");

    private final JLabel labelWeight = new JLabel("PESO :");
    private final JLabel labelBoxes = new JLabel("CAJAS :");
    private final JLabel labelPallets = new JLabel("TARIMAS :");
    private final JLabel labelMainTitle = new JLabel("CONSULTAR PRODUCCIÓN");

    private final String[] arrStrTitleColums = {"FECHA", "HORA", "MARBETE", "PRODUCTO", "DESCRIPCION", "NUM CARGA", "CAJAS", "PESO TOTAL", "RESPONSABLE"};
    private final Integer[] arrIntSizeColumns = {100, 85, 100, 75, 290, 100, 60, 90, 247};//TAMAÑO DE LAS COLUMNAS
    public JTable tableMain = this.setPropsTable(arrStrTitleColums, arrIntSizeColumns);
    private final JScrollPane scrollPaneTableMain = new JScrollPane(tableMain);

    public JFileChooser fileChooserPdf;
    public JFileChooser fileChooserXlsx;

    private static final Font FONT_LABEL = new Font("Agency FB", Font.BOLD, 21);
    private static final Font FONT_TOTAL = new Font("Agency FB", Font.BOLD, 21);
    private static final Font FONT_DATE_CHOOSER = new Font("Agency FB", Font.BOLD, 15);
    private static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 15);
    private static final Font FONT_MAIN_TITLE = new Font("Arial", Font.BOLD, 30);

    private static final Color COLOR_TEXT_LABEL = Color.BLACK;
    private static final Color COLOR_TEXT_TOTAL = new Color(12, 159, 10);
    private static final Color COLOR_TEXT_BUTTON = Color.BLACK;
    private static final Color COLOR_TEXT_MAIN_TITLE = Color.YELLOW;
    private static final Color COLOR_BORDER_BUTTON = Color.GRAY;
    private static final Color COLOR_BACKGROUND_PANEL_MAIN = new Color(10, 10, 10, 34);
    private static final Color COLOR_BACKGROUND_PANEL_TITLE = new Color(10, 10, 10, 34);

    private static final Integer ALING_TEXT_LABEL = SwingConstants.RIGHT;
    private static final Integer ALING_TEXT_TOTAL = SwingConstants.LEFT;
    private static final Integer ALING_TEXT_DATE_CHOOSER = SwingConstants.CENTER;
    private static final Integer ALING_TEXT_COMBOBOX = SwingConstants.LEFT;
    private static final Integer ALING_TEXT_BUTTON = SwingConstants.CENTER;
    private static final Integer ALING_TEXT_MAIN_TITLE = SwingConstants.CENTER;

    private static final String PATH_IMG_XLS = "/com/felder/img/excel.png";
    private static final String PATH_IMG_PDF = "/com/felder/img/pdf.png";

    private static final Border BORDER_PANEL_MAIN = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
    private static final Border BORDER_PANEL_TITLE = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);

    public ConsultarView(String strTit, Integer intAlt, Integer intAncho) {
        super(strTit, intAlt, intAncho);
        this.createFileChooser();
        this.createButtonImages();
        this.addControls();
        this.getDateChooserTo().setEnabled(false);
        this.buttonSearch.setEnabled(false);
        this.repaint();
    }

    private void createButtonImages() {
        this.buttonExcel = createButtonImage(PATH_IMG_XLS);
        this.buttonPdf = createButtonImage(PATH_IMG_PDF);
    }

    private void createFileChooser() {
        this.fileChooserPdf = this.createFileChooser(".PDF", false, false, JFileChooser.FILES_ONLY, true);
        this.fileChooserXlsx = this.createFileChooser(".XLSX", false, false, JFileChooser.FILES_ONLY, true);
    }

    private void addControls() {
        this.addDateChooser(dateChooserFrom, "yyyy-MM-dd", FONT_DATE_CHOOSER, ALING_TEXT_DATE_CHOOSER, 155, 60, 150, 30);
        this.addDateChooser(dateChooserTo, "yyyy-MM-dd", FONT_DATE_CHOOSER, ALING_TEXT_DATE_CHOOSER, 455, 60, 150, 30);
        this.addLabel(this.labelDateFrom, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 50, 60, 100, 30);
        this.addLabel(this.labelDateTo, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 350, 60, 100, 30);
        this.addLabel(this.labelProducts, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 610, 60, 100, 30);
        this.addLabel(this.labelWeight, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 30, 515, 80, 30);
        this.addLabel(this.labelTotalWeight, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_TOTAL, 140, 515, 80, 30);
        this.addLabel(this.labelBoxes, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 30, 540, 80, 30);
        this.addLabel(this.labelTotalBoxes, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_TOTAL, 140, 540, 80, 30);
        this.addLabel(this.labelPallets, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LABEL, 30, 565, 80, 30);
        this.addLabel(this.labelTotalPallets, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_TOTAL, 140, 565, 80, 30);
        this.addComboBox(this.comboProducts, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_COMBOBOX, 720, 60, 300, 30);
        this.addButton(this.buttonSearch, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_BUTTON, 1050, 60, 100, 30);
        this.addButton(this.buttonExcel, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_BUTTON, 945, 540, 100, 30);
        this.addButton(this.buttonPdf, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_BUTTON, 1050, 540, 100, 30);

        this.addLabel(this.labelMainTitle, FONT_MAIN_TITLE, COLOR_TEXT_MAIN_TITLE, ALING_TEXT_MAIN_TITLE, 10, 10, 1170, 40);
        this.addPanel(this.panelTitle, COLOR_BACKGROUND_PANEL_MAIN, BORDER_PANEL_MAIN, 10, 10, 1170, 40);
        this.addPanel(this.panelMain, COLOR_BACKGROUND_PANEL_TITLE, BORDER_PANEL_TITLE, 10, 10, 1170, 100);
        this.addScrollPane(scrollPaneTableMain, 10, 120, 1170, 390);
    }

    public JComboBox getComboProducts() {
        return comboProducts;
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public JDateChooser getDateChooserFrom() {
        return dateChooserFrom;
    }

    public JDateChooser getDateChooserTo() {
        return dateChooserTo;
    }

    public JTable getTableMain() {
        return tableMain;
    }

    public JButton getButtonExcel() {
        return buttonExcel;
    }

    public JButton getButtonPdf() {
        return buttonPdf;
    }

    public JLabel getLabelTotalWeight() {
        return labelTotalWeight;
    }

    public JLabel getLabelTotalBoxes() {
        return labelTotalBoxes;
    }

    public JLabel getLabelTotalPallets() {
        return labelTotalPallets;
    }

    public JFileChooser getFileChooserPdf() {
        return fileChooserPdf;
    }

    public JFileChooser getFileChooserXlsx() {
        return fileChooserXlsx;
    }
}
