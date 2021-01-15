package com.felder.view;

import com.felder.util.CustomRenderForJCombobox;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public abstract class AbstractJFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public String strTitle;
    public Integer intHeight;
    public Integer intWidth;
    private static final String PATH_IMG_LOGO = "/com/felder/img/globoMini.png";

    public AbstractJFrame(String strTit, Integer intAlt, Integer intAncho) {
        this.strTitle = strTit;
        this.intHeight = intAlt;
        this.intWidth = intAncho;
        this.setPropiertiesJFrame();
    }

    private void setPropiertiesJFrame() {
        this.setLayout(null);
        this.setSize(intWidth, intHeight);
        this.setTitle(strTitle);
        this.setType(Window.Type.NORMAL);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);
        this.setIconApp();
        this.setVisible(true);

    }

    private void setIconApp() {
        try {
            InputStream imgStream = getClass().getResourceAsStream(PATH_IMG_LOGO);
            BufferedImage ingBuffered = ImageIO.read(imgStream);
            this.setIconImage(ingBuffered);
        } catch (Exception ex) {
            System.out.println("Error al cargar icono : " + PATH_IMG_LOGO);
        }
    }

    public void addLabel(JLabel label, Font font, Color color, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        label.setHorizontalAlignment(aling);
        label.setFont(font);
        label.setForeground(color);
        this.add(label);
        label.setBounds(x, y, w, h);
    }

    public void addPanel(JPanel panel, Color colorBackGround, Border border, Integer x, Integer y, Integer w, Integer h) {
        this.add(panel);
        panel.setBounds(x, y, w, h);
        panel.setBackground(colorBackGround);
        panel.setBorder(border);
    }

    public void addScrollPane(JScrollPane scrl, Integer x, Integer y, Integer w, Integer h) {
        this.add(scrl);
        scrl.setBounds(x, y, w, h);
    }

    public JTable setPropsTable(String[] strNomColum, Integer[] arrTamCol) {
        Font fntTbl = new Font("Agency FB", Font.BOLD, 21);
        Integer tblRowHeight = 25;
        /*SE CREA DefaultTableModel CONTENDRA TODOS LOS DATOS DE LA TABLA */
        DefaultTableModel dtm = new DefaultTableModel(strNomColum, 0) {/*HACE QUE LAS CELDAS SEAN NO EDITABLES*/

            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return false;
                } else {
                    return false;
                }
            }
        };
        /*SE CREA TABLA*/
        JTable tbl = new TableCrossOver(dtm, fntTbl);
        tbl.setEnabled(false);

        /*ELIMINA LINEAS INTERNAS DE LA TABLA*/
//        tbl.setShowGrid(false);

        /* ALTURA DEL RENGLON */
        tbl.setRowHeight(tblRowHeight);

        /*DESHABILITA AJUSTE AUTOMATICO DE LAS COLUMNAS*/
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //DESHABILITA SELECCION MULTIPLE
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*NO ARRASTRAR COLUMNA*/
        tbl.getTableHeader().setReorderingAllowed(false);
//        System.out.println("hola:" + tblSup.getModel().getRowCount());

        //CAMBIA EL CURSOR A MANITA CUANDO PASA SOBRE LA TABLA
        tbl.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //CAMBIA EL CURSOR A MANITA CUANDO PASA SOBRE LA CABECERA DE LA TABLA
        tbl.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));

        //PROPIEDAD PARA ORDENAR AL HACER CLICK EN EL ENCAVEZADO
        tbl.setAutoCreateRowSorter(true);

        //PROPIEDAD LE DA ESTILO AL ENCABEZADO DE LA TABLA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tbl.getColumnModel().getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tbl.getColumnModel().getColumn(i).setPreferredWidth(arrTamCol[i]);
        }
        return tbl;
    }

    public void addButton(JButton button, Font font, Color colorText, Color colorBorder, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        button.setHorizontalAlignment(aling);
        button.setFont(font);
        button.setForeground(colorText);
        button.setBorder(BorderFactory.createLineBorder(colorBorder));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(button);
        button.setBounds(x, y, w, h);
    }

    public void addDateChooser(JDateChooser dateChooser, String strFmt, Font fnt, int intAling, int intX, int intY, int intW, int intH) {
        this.add(dateChooser);
        dateChooser.setBounds(intX, intY, intW, intH);
        dateChooser.getDateEditor().setDateFormatString(new SimpleDateFormat(strFmt).toPattern());
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setEditable(false);
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setFont(fnt);
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setHorizontalAlignment(intAling);
        dateChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void addComboBox(JComboBox comboBox, Font font, Color color, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        this.add(comboBox);
        comboBox.setBounds(x, y, w, h);
        ((JLabel) comboBox.getRenderer()).setHorizontalAlignment(aling);
        comboBox.setRenderer(new CustomRenderForJCombobox(aling, color));
        comboBox.setFont(font);
        comboBox.setForeground(color);
        AutoCompleteDecorator.decorate(comboBox);
        comboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public JButton createButtonImage(String path) {
        JButton button = new JButton(FilenameUtils.getBaseName(path).toUpperCase());
        try {
            button = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(path))));
        } catch (Exception ex) {
            System.out.println("Error al cargar imagen : " + path);
        }
        return button;
    }

    //PROPIEDADES DEL FILESHOOSER
    public JFileChooser createFileChooser(String filters, Boolean multipleFiles, Boolean allFilerOption, Integer selectionMode, Boolean disableNewFolder) {
        JFileChooser fileChooser = new JFileChooser();
        //Indicamos que podemos seleccionar varios ficheros
        fileChooser.setMultiSelectionEnabled(multipleFiles);
        //Indicamos lo que podemos seleccionar
        fileChooser.setFileSelectionMode(selectionMode);
        //deshabilita opcion todos los archivos
        fileChooser.setAcceptAllFileFilterUsed(allFilerOption);
        if (disableNewFolder) {
            this.disableNewFolderButton(fileChooser);//METODO DESHABILITA BOTON DE CREAR CARPETA NUEVA;
        }
        fileChooser.setFileFilter(new FileNameExtensionFilter(filters, filters));
        return fileChooser;
    }

    private void disableNewFolderButton(Container c) {
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) {
            Component comp = c.getComponent(i);
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;
                Icon icon = b.getIcon();
                if (icon != null
                        && icon == UIManager.getIcon("FileChooser.newFolderIcon")) {
                    b.setEnabled(false);
                }
            } else if (comp instanceof Container) {
                disableNewFolderButton((Container) comp);
            }
        }
    }
}
