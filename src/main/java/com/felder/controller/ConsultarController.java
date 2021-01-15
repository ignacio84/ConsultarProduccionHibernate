package com.felder.controller;
import com.felder.model.pojo.Produccion;
import com.felder.model.service.IProductService;
import com.felder.model.service.IProductionService;
import com.felder.model.service.ProductServiceImpl;
import com.felder.model.service.ProductionServiceImpl;
import com.felder.util.DateImpl;
import com.felder.util.IDate;
import com.felder.util.IPdf;
import com.felder.util.ITable;
import com.felder.util.IXlsx;
import com.felder.util.PdfImpl;
import com.felder.util.Table;
import com.felder.util.XlxsImp;
import com.felder.view.ConsultarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultarController implements ActionListener, PropertyChangeListener {

    private static final String STR_MSJ_COMBOBOX_DEFAULT = "SELECCIONAR ARTICULO";
    private static final String STR_MSJ_COMBOBOX_ALL = "TODOS";
    private static final DecimalFormat FORMAT_TWO_DECIMAL = new DecimalFormat("###,##0.00");

    private final IProductService productService;
    private final IProductionService productionService;
    private final ITable table;
    private final IDate date;
    private final IXlsx xlsx;
    private final IPdf pdf;

    private List<Produccion> listProduccion = new ArrayList<>();
    private final ConsultarView vConsultar = new ConsultarView(" Felder Foods ( Consultar Produccion )", 650, 1195);

    public JFileChooser fileChooserXlsx = new JFileChooser();//directorio donde se guardara 
    public JFileChooser fileChooserPdf = new JFileChooser();//directorio donde se guardara 

    public ConsultarController() {
        this.productService = new ProductServiceImpl();
        this.productionService = new ProductionServiceImpl();
        this.table = new Table();
        this.date = new DateImpl();
        this.xlsx = new XlxsImp();
        this.pdf = new PdfImpl();
        this.loadCombobox();
        this.addListener();
    }

    //METODO CARGA INFORMACION DE PEDIDOS EN EL COMBOBOX
    private void loadCombobox() {
        try {
            List<String> listProducts = this.productService.getAll()
                    .stream()
                    .map(data -> data.getClaveProducto() + " - " + data.getDescripcion()).collect(Collectors.toList());
            listProducts.add(0, STR_MSJ_COMBOBOX_DEFAULT);
            listProducts.add(1, STR_MSJ_COMBOBOX_ALL);
            vConsultar.getComboProducts().setModel(new DefaultComboBoxModel(listProducts.toArray()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListener() {
        vConsultar.getButtonSearch().addActionListener(this);
        vConsultar.getButtonExcel().addActionListener(this);
        vConsultar.getButtonPdf().addActionListener(this);
        vConsultar.getComboProducts().addActionListener(this);
        vConsultar.getDateChooserFrom().getDateEditor().addPropertyChangeListener(this);
        vConsultar.getDateChooserTo().getDateEditor().addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vConsultar.getComboProducts())) {
            this.enabledButtonSearch();
        }
        if (e.getSource().equals(vConsultar.getButtonSearch())) {
            try {
                this.listProduccion = this.productionService.findProductionByDateAndKey(
                        this.date.formatingDate(vConsultar.getDateChooserFrom().getDate(), 0, 0, 0),
                        this.date.formatingDate(vConsultar.getDateChooserTo().getDate(), 23, 59, 59),
                        vConsultar.getComboProducts().getSelectedItem().toString().split("-")[0].trim().replace(STR_MSJ_COMBOBOX_ALL, "%"));
                this.sumPallets();
                this.sumBoxes();
                this.sumWeight();
                this.loadTable();
                this.clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if ((e.getSource().equals(vConsultar.getButtonExcel())
                || e.getSource().equals(vConsultar.getButtonPdf()))
                && this.listProduccion.size() > 0) {
            this.makeDocument(e.getSource());
        }
    }

    private void makeDocument(Object source) {
        try {
            if (source.equals(vConsultar.getButtonExcel())) {
                if (vConsultar.getFileChooserXlsx().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    this.xlsx.makeFromProduccion(this.listProduccion, vConsultar.getFileChooserXlsx().getSelectedFile().getPath());
                    vConsultar.getFileChooserXlsx().setSelectedFile(new File(""));
                    return;
                }
            }
            if (source.equals(vConsultar.getButtonPdf())) {
                if (vConsultar.getFileChooserPdf().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    this.pdf.makeFromProduccion(this.listProduccion, vConsultar.getFileChooserPdf().getSelectedFile().getPath());
                    vConsultar.getFileChooserPdf().setSelectedFile(new File(""));
                    return;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vConsultar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        vConsultar.getDateChooserFrom().setDate(null);
        vConsultar.getDateChooserTo().setDate(null);
        vConsultar.getComboProducts().setSelectedIndex(0);
    }

    private void loadTable() {
        List<Object[]> listObject = new ArrayList<>();
        this.listProduccion
                .stream()
                .forEach((data) -> {
                    listObject.add(new Object[]{
                        data.getFecha(),
                        data.getHora(),
                        data.getFolio(),
                        data.getCodigoProducto(),
                        data.getDescripcion(),
                        data.getCarga(),
                        data.getTotalCajas(),
                        FORMAT_TWO_DECIMAL.format(Double.valueOf(data.getPeso())),
                        data.getResponsable()
                    });
                });
        this.table.addDataToDefaultTableModel((DefaultTableModel) vConsultar.getTableMain().getModel(), listObject);
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

    private void enabledButtonSearch() {
        if (vConsultar.getDateChooserFrom().getDate() != null
                && vConsultar.getDateChooserTo().getDate() != null
                && vConsultar.getComboProducts().getSelectedIndex() > 0) {
            vConsultar.getButtonSearch().setEnabled(true);
            return;
        }
        vConsultar.getButtonSearch().setEnabled(false);

    }

    private void sumWeight() {
        Double suma = this.listProduccion.stream()
                .mapToDouble(data -> Double.valueOf(data.getPeso()))
                .sum();
        vConsultar.getLabelTotalWeight().setText(FORMAT_TWO_DECIMAL.format(suma));
    }

    private void sumBoxes() {
        Integer suma = this.listProduccion.stream()
                .mapToInt(data -> data.getTotalCajas())
                .sum();
        vConsultar.getLabelTotalBoxes().setText(suma.toString());
    }

    private void sumPallets() {
//        Integer suma = this.listProduccion.stream().map(Produccion::getFolio).distinct().collect(Collectors.toList()).size();
        vConsultar.getLabelTotalPallets().setText(String.valueOf(this.listProduccion.size()));
    }

}
