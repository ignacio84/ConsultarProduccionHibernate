package com.felder.view;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ConsultarView extends AbstractJFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel panelTitle = new JPanel();
    private final JPanel panelMain = new JPanel();

    private final JLabel labelDateFrom = new JLabel("FECHA DESDE :");
    private final JLabel labelDateTo = new JLabel("FECHA HASTA :");
    private final JLabel labelProducts = new JLabel("PRODUCTO :");
    private final JLabel labelBatch = new JLabel("LOTE :");

    private final JRadioButton radioButtonPallets = new JRadioButton("Tarimas");
    private final JRadioButton radioButtonBoxes = new JRadioButton("Cajas");
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private final JDateChooser dateChooserFrom = new JDateChooser();
    private final JDateChooser dateChooserTo = new JDateChooser();

    private JComboBox comboProducts = new JComboBox();
    private JTextField textBatch = new JTextField();
    private JButton buttonSearch = new JButton("BUSCAR");

    private final JLabel labelWeight = new JLabel("PESO :");
    private final JLabel labelBoxes = new JLabel("CAJAS :");
    private final JLabel labelPallets = new JLabel("TARIMAS :");
    private final JLabel labelMainTitle = new JLabel("CONSULTAR PRODUCCIÓN");

    private JLabel labelTotalWeight = new JLabel("0.00");
    private JLabel labelTotalBoxes = new JLabel("0");
    private JLabel labelTotalPallets = new JLabel("0");

    private JButton buttonExcel;
    private JButton buttonPdf;

    private final String[] arrStrTitleColumsPallets = {"FECHA", "HORA", "MARBETE", "PRODUCTO", "DESCRIPCION", "LOTE", "CAJAS", "PESO TOTAL", "RESPONSABLE"};
    private final Integer[] arrIntSizeColumnsPallets = {100, 85, 100, 75, 290, 100, 60, 90, 247};//TAMAÑO DE LAS COLUMNAS
    private JTable tablePallets = this.setPropsTable(arrStrTitleColumsPallets, arrIntSizeColumnsPallets);

    private final String[] arrStrTitleColumsBoxes = {"CODIGO BARRAS", "FECHA", "HORA", "MARBETE", "PRODUCTO", "DESCRIPCION", "LOTE", "PESO", "RESPONSABLE"};
    private final Integer[] arrIntSizeColumnsBoxes = {175, 100, 75, 115, 62, 280, 100, 60, 180};//TAMAÑO DE LAS COLUMNAS
    private JTable tableBoxes = this.setPropsTable(arrStrTitleColumsBoxes, arrIntSizeColumnsBoxes);

    private JScrollPane scrollPaneTableMain = new JScrollPane(tablePallets);

    private JFileChooser fileChooserPdf;
    private JFileChooser fileChooserXlsx;

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

    private static final Integer ALING_TEXT_RIGHT = SwingConstants.RIGHT;
    private static final Integer ALING_TEXT_LEFT = SwingConstants.LEFT;

    private static final Integer ALING_TEXT_CENTER = SwingConstants.CENTER;

    private static final String PATH_IMG_XLS = "/com/felder/img/excel.png";
    private static final String PATH_IMG_PDF = "/com/felder/img/pdf.png";

    private static final Border BORDER_PANEL_MAIN = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
    private static final Border BORDER_PANEL_TITLE = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);

    public ConsultarView(String strTit, Integer intAlt, Integer intAncho) {
        super(strTit, intAlt, intAncho);
        this.radioButtonPallets.setSelected(true);
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

        this.addLabel(this.labelDateFrom, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 50, 60, 100, 30);
        this.addLabel(this.labelDateTo, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 350, 60, 100, 30);
        this.addLabel(this.labelProducts, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 610, 60, 100, 30);
        this.addLabel(this.labelBatch, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 50, 100, 100, 30);
        this.addLabel(this.labelWeight, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 30, 515, 80, 30);
        this.addLabel(this.labelTotalWeight, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_LEFT, 140, 515, 80, 30);
        this.addLabel(this.labelBoxes, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 30, 540, 80, 30);
        this.addLabel(this.labelTotalBoxes, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_LEFT, 140, 540, 80, 30);
        this.addLabel(this.labelPallets, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_RIGHT, 30, 565, 80, 30);
        this.addLabel(this.labelTotalPallets, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_LEFT, 140, 565, 80, 30);
        this.addDateChooser(dateChooserFrom, "yyyy-MM-dd", FONT_DATE_CHOOSER, ALING_TEXT_CENTER, 155, 60, 150, 30);
        this.addDateChooser(dateChooserTo, "yyyy-MM-dd", FONT_DATE_CHOOSER, ALING_TEXT_CENTER, 455, 60, 150, 30);
        this.addComboBox(this.comboProducts, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_LEFT, 720, 60, 300, 30);
        this.addTextField(this.textBatch, FONT_TOTAL, COLOR_TEXT_TOTAL, ALING_TEXT_CENTER, 155, 100, 150, 30);
        this.addRadioButton(radioButtonPallets, buttonGroup, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LEFT, 355, 100, 100, 30);
        this.addRadioButton(radioButtonBoxes, buttonGroup, FONT_LABEL, COLOR_TEXT_LABEL, ALING_TEXT_LEFT, 455, 100, 100, 30);
        this.addButton(this.buttonSearch, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_CENTER, 1050, 60, 100, 30);
        this.addButton(this.buttonExcel, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_CENTER, 945, 540, 100, 30);
        this.addButton(this.buttonPdf, FONT_BUTTON, COLOR_TEXT_BUTTON, COLOR_BORDER_BUTTON, ALING_TEXT_CENTER, 1050, 540, 100, 30);
        this.addLabel(this.labelMainTitle, FONT_MAIN_TITLE, COLOR_TEXT_MAIN_TITLE, ALING_TEXT_CENTER, 10, 10, 1170, 40);
        this.addPanel(this.panelTitle, COLOR_BACKGROUND_PANEL_MAIN, BORDER_PANEL_MAIN, 10, 10, 1170, 40);
        this.addPanel(this.panelMain, COLOR_BACKGROUND_PANEL_TITLE, BORDER_PANEL_TITLE, 10, 10, 1170, 130);
        this.addScrollPane(scrollPaneTableMain, 10, 150, 1170, 360);

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

    public JTable getTablePallets() {
        return tablePallets;
    }

    public void setTablePallets(JTable tablePallets) {
        this.tablePallets = tablePallets;
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

    public JTextField getTextBatch() {
        return textBatch;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JRadioButton getRadioButtonPallets() {
        return radioButtonPallets;
    }

    public JRadioButton getRadioButtonBoxes() {
        return radioButtonBoxes;
    }

    public JTable getTableBoxes() {
        return tableBoxes;
    }

    public JScrollPane getScrollPaneTableMain() {
        return scrollPaneTableMain;
    }

}
