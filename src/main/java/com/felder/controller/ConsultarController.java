package com.felder.controller;

import com.felder.model.entity.Pallet;
import com.felder.model.service.IItemService;
import com.felder.model.service.IPalletService;
import com.felder.model.service.ItemServiceImpl;
import com.felder.model.service.PalletServiceImpl;
import com.felder.swing.DateImpl;
import com.felder.swing.IDate;
import com.felder.swing.ITable;
import com.felder.swing.Table;
import com.felder.utils.service.IPdfService;
import com.felder.utils.service.IXlsService;
import com.felder.utils.service.PdfServiceImpl;
import com.felder.utils.service.XlsServiceImpl;
import com.felder.view.ConsultarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultarController implements KeyListener, ActionListener, PropertyChangeListener {

    private IItemService itemService = new ItemServiceImpl();
    private IPalletService palletService = new PalletServiceImpl();
    private IXlsService xlsxService = new XlsServiceImpl();
    private IPdfService pdfService = new PdfServiceImpl();

    private static final String STR_MSJ_COMBOBOX_DEFAULT = "SELECCIONAR ARTICULO";
    private static final String STR_MSJ_COMBOBOX_ALL = "TODOS";
    private static final DecimalFormat FORMAT_TWO_DECIMAL = new DecimalFormat("###,##0.00");
    private static final DecimalFormat FORMAT_INTEGER = new DecimalFormat("###,###");

    private final ITable table;
    private final IDate date;

//    private List<Produccion> listProduccion = new ArrayList<>();
    private List<Pallet> listPallets = new ArrayList<>();
    private final ConsultarView vConsultar = new ConsultarView(" Felder Foods ( Consultar Produccion )", 650, 1195);

    public JFileChooser fileChooserXlsx = new JFileChooser();//directorio donde se guardara 
    public JFileChooser fileChooserPdf = new JFileChooser();//directorio donde se guardara 

    public ConsultarController() {
        this.table = new Table();
        this.date = new DateImpl();
        this.loadCombobox();
        this.addListener();
    }

    //METODO CARGA INFORMACION DE PEDIDOS EN EL COMBOBOX
    private void loadCombobox() {
        try {
            List<String> listProducts = this.itemService.findAll()
                    .stream()
                    .map(data -> data.getClaveProducto() + " - " + data.getDescripcion()).collect(Collectors.toList());
            listProducts.add(0, STR_MSJ_COMBOBOX_DEFAULT);
            listProducts.add(1, STR_MSJ_COMBOBOX_ALL);
            vConsultar.getComboProducts().setModel(new DefaultComboBoxModel(listProducts.toArray()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //AGREGA ESCUCHADORES A CONTROLES
    private void addListener() {
        vConsultar.getButtonSearch().addActionListener(this);
        vConsultar.getButtonExcel().addActionListener(this);
        vConsultar.getButtonPdf().addActionListener(this);
        vConsultar.getComboProducts().addActionListener(this);
        vConsultar.getTextBatch().addKeyListener(this);
        vConsultar.getDateChooserFrom().getDateEditor().addPropertyChangeListener(this);
        vConsultar.getDateChooserTo().getDateEditor().addPropertyChangeListener(this);
        vConsultar.getRadioButtonPallets().addActionListener(this);
        vConsultar.getRadioButtonBoxes().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vConsultar.getComboProducts())) {
            this.enabledButtonSearch();
        }
        if (e.getSource().equals(vConsultar.getRadioButtonPallets()) || e.getSource().equals(vConsultar.getRadioButtonBoxes())) {
            this.loadTables();
        }
        if (e.getSource().equals(vConsultar.getButtonSearch())) {
            try {
                LocalDateTime from = (vConsultar.getDateChooserFrom().getDate() == null) ? LocalDateTime.of(2000, Month.JANUARY, 01, 0, 1)
                        : vConsultar.getDateChooserFrom().getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                LocalDateTime to = (vConsultar.getDateChooserTo().getDate() == null) ? LocalDateTime.now()
                        : vConsultar.getDateChooserTo().getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                                .plusHours(23)
                                .plusMinutes(59)
                                .plusSeconds(59);
                String itemCode = vConsultar.getComboProducts().getSelectedItem().toString().split("-")[0].trim().replace(STR_MSJ_COMBOBOX_ALL, "%");
                String batch = (vConsultar.getTextBatch().getText().trim().length() > 0) ? vConsultar.getTextBatch().getText().trim() : "%";
                Pallet pallet = new Pallet(itemCode, batch, from, to);//CREA OBJETO DE TIPO PALLET
                this.listPallets = this.palletService.findByDateAndItemAndBatch(pallet);//REALIZA CONSULTA
                this.sumPallets();
                this.sumBoxes();
                this.sumWeight();
                this.loadTables();
                this.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if ((e.getSource().equals(vConsultar.getButtonExcel())
                || e.getSource().equals(vConsultar.getButtonPdf()))
                && this.listPallets.size() > 0) {
            this.makeDocument(e.getSource());
        }
    }

    //GENERA DOCUMENTOS PDF, XLSX
    private void makeDocument(Object source) {
        try {
            if (source.equals(vConsultar.getButtonExcel())) {
                if (vConsultar.getFileChooserXlsx().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (vConsultar.getRadioButtonPallets().isSelected()) {
                        this.xlsxService.makeFromPallets(listPallets, vConsultar.getFileChooserXlsx().getSelectedFile().getPath());
                        vConsultar.getFileChooserXlsx().setSelectedFile(new File(""));
                        return;
                    }
                    this.xlsxService.makeFromBoxes(listPallets, vConsultar.getFileChooserXlsx().getSelectedFile().getPath());
                    vConsultar.getFileChooserXlsx().setSelectedFile(new File(""));
                    return;
                }
            }
            if (source.equals(vConsultar.getButtonPdf())) {
                if (vConsultar.getFileChooserPdf().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (vConsultar.getRadioButtonPallets().isSelected()) {
                        this.pdfService.makeFromPallets(this.listPallets, vConsultar.getFileChooserPdf().getSelectedFile().getPath());
                        vConsultar.getFileChooserPdf().setSelectedFile(new File(""));
                        return;
                    }
                    this.pdfService.makeFromBoxes(this.listPallets, vConsultar.getFileChooserPdf().getSelectedFile().getPath());
                    vConsultar.getFileChooserPdf().setSelectedFile(new File(""));
                    return;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //LIMPIA CAMPOS DE BUSQUEDA DEL FORMULARIO
    private void clearForm() {
        vConsultar.getDateChooserFrom().setDate(null);
        vConsultar.getDateChooserTo().setDate(null);
        vConsultar.getTextBatch().setText("");
        vConsultar.getComboProducts().setSelectedIndex(0);
    }

    private void loadTables() {
        if (vConsultar.getRadioButtonPallets().isSelected()) {
            this.loadTablePallets();
            return;
        }
        this.loadTableBoxes();
    }

    private void loadTablePallets() {
        List<Object[]> listObject = new ArrayList<>();
        this.listPallets
                .stream()
                .forEach((data) -> {
                    listObject.add(new Object[]{
                        data.getFecha().toLocalDate(),
                        data.getFecha().toLocalTime(),
                        data.getFolio(),
                        data.getCodigoProducto(),
                        data.getLines().get(0).getDescripcion(),
                        data.getLote(),
                        data.getNumeroCajas(),
                        FORMAT_TWO_DECIMAL.format(Double.valueOf(data.getPeso())),
                        data.getResponsable()
                    });
                });
        this.table.addDataToDefaultTableModel((DefaultTableModel) vConsultar.getTablePallets().getModel(), listObject);
        this.vConsultar.getScrollPaneTableMain().getViewport().add(vConsultar.getTablePallets());
    }

    private void loadTableBoxes() {
        List<Object[]> listObject = new ArrayList<>();
        this.listPallets
                .stream()
                .forEach((data) -> {
                    data.getLines()
                            .stream()
                            .forEach((d) -> {
                                listObject.add(new Object[]{
                                    d.getCodigoBarras(),
                                    data.getFecha().toLocalDate(),
                                    data.getFecha().toLocalTime(),
                                    data.getFolio(),
                                    data.getCodigoProducto(),
                                    data.getLines().get(0).getDescripcion(),
                                    data.getLote(),
                                    FORMAT_TWO_DECIMAL.format(Double.valueOf(d.getPeso())),
                                    data.getResponsable()
                                });
                            });
                });

        this.table.addDataToDefaultTableModel((DefaultTableModel) vConsultar.getTableBoxes().getModel(), listObject);
        this.vConsultar.getScrollPaneTableMain().getViewport().add(vConsultar.getTableBoxes());
    }

    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("date")) {
            if (vConsultar.getDateChooserFrom().getDate() != null) {
                vConsultar.getDateChooserTo().setEnabled(true);
                vConsultar.getDateChooserTo().setMinSelectableDate(vConsultar.getDateChooserFrom().getDate());
            }
            if (vConsultar.getDateChooserTo() != null) {
                vConsultar.getDateChooserFrom().setMaxSelectableDate(vConsultar.getDateChooserTo().getDate());
            }
            this.enabledButtonSearch();
        }
    }

    //ACTIVA Y DESACTIVA BOTON BUSCAR
    private void enabledButtonSearch() {
        if (vConsultar.getDateChooserFrom().getDate() != null
                && vConsultar.getDateChooserTo().getDate() != null
                && vConsultar.getComboProducts().getSelectedIndex() > 0) {
            vConsultar.getButtonSearch().setEnabled(true);
            return;//CON LA FUNCION RETURN SE SALE DEL METODO Y NO EXECUTA EL RESTO DEL CODIGO
        }
        if (vConsultar.getTextBatch().getText().trim().length() > 0
                && vConsultar.getComboProducts().getSelectedIndex() > 0) {
            vConsultar.getButtonSearch().setEnabled(true);
            return;//CON LA FUNCION RETURN SE SALE DEL METODO Y NO EXECUTA EL RESTO DEL CODIGO
        }
        vConsultar.getButtonSearch().setEnabled(false);

    }

    private void sumWeight() {
        Double suma = this.listPallets.stream()
                .mapToDouble(data -> Double.valueOf(data.getPeso()))
                .sum();
        vConsultar.getLabelTotalWeight().setText(FORMAT_TWO_DECIMAL.format(suma));
    }

    private void sumBoxes() {
        Integer suma = this.listPallets.stream()
                .mapToInt(data -> data.getNumeroCajas())
                .sum();
        vConsultar.getLabelTotalBoxes().setText(FORMAT_INTEGER.format(suma));
    }

    private void sumPallets() {
//        Integer suma = this.listProduccion.stream().map(Produccion::getFolio).distinct().collect(Collectors.toList()).size();
        vConsultar.getLabelTotalPallets().setText(String.valueOf(this.listPallets.size()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.enabledButtonSearch();
    }
}
