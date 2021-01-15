package com.felder.util;

import com.felder.model.pojo.Produccion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class PdfImpl extends AbstractPdf implements IPdf {

    private static final float FLT_SIZECOLUMNS[] = new float[]{25, 20, 25, 20, 100, 30, 20, 25, 25};
    private final Font FNT_TABLE_TITLE = new Font(Font.FontFamily.UNDEFINED, 6, Font.BOLD, new BaseColor(0, 0, 0));
    private final Font FNT_TABLE_CONTENT = new Font(Font.FontFamily.UNDEFINED, 6, Font.NORMAL, new BaseColor(0, 0, 0));
    private final Rectangle INT_PAGE_SIZE = PageSize.LETTER;
    private static final float CELL_HEIGHT = 14;
    private static final Integer INT_PADDING = 100;
    private static final Integer INT_MARGIN_TOP = 30;
    private static final Integer INT_MARGIN_BOTTOM = 30;
    private static final Integer INT_MARGIN_LEFT = 30;
    private static final Integer INT_MARGIN_RIGHT = 30;
    private static final BaseColor BACKGROUND_COLOR_TITLE = BaseColor.LIGHT_GRAY;
    private static final BaseColor BACKGROUND_COLOR_CONTENT = BaseColor.WHITE;

    private static final DecimalFormat FORMAT_TWO_DECIMAL = new DecimalFormat("###,##0.00");

    private PdfPTable table;
    private List<Produccion> listProduccion;
    private Document document;
    private PdfWriter writer;

    @Override
    public void makeFromProduccion(List<Produccion> listProduccion, String path) throws Exception {
        path += ".PDF";
        this.listProduccion = listProduccion;
        this.document = new Document(INT_PAGE_SIZE, INT_MARGIN_LEFT, INT_MARGIN_RIGHT, INT_MARGIN_TOP, INT_MARGIN_BOTTOM);//hoja tamaño carta y sus margenes
        this.writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        writer.setPageEvent(this);
        document.open();
        this.setCellHeight(this.CELL_HEIGHT);
        this.table = new PdfPTable(FLT_SIZECOLUMNS);//TAMAÑO DE LAS COLUMNAS
        table.setWidthPercentage(INT_PADDING);//PADDING
        this.renderTitle();
        this.renderContent();
        this.renderTotal();
        document.add(this.table);
        document.close();
        Desktop.getDesktop().open(new File(path));
    }

    private void renderTitle() {
        //TITULOS DE TABLA
        this.table.addCell(this.getCell("FECHA", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("HORA", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("FOLIO", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("PRODUCTO", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("DESCRIPCION", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("CARGA", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("CAJAS", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("PESO TOTAL", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("RESPONSABLE", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
    }

    private void renderContent() {
        for (Produccion produccion : this.listProduccion) {
            table.addCell(this.getCell(produccion.getFecha(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getHora(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getFolio().toString(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getCodigoProducto(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getDescripcion(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getCarga(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getTotalCajas().toString(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(FORMAT_TWO_DECIMAL.format(Double.valueOf(produccion.getPeso())), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
            table.addCell(this.getCell(produccion.getResponsable(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
        }
    }

    private void renderTotal() {
        table.addCell(this.getCell("TOTAL :  ", BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_RIGHT, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 6, 1, 0));
        table.addCell(this.getCell(this.sumBoxes(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        table.addCell(this.getCell(this.sumWeight(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        table.addCell(this.getCell("", BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 0));
    }

    private String sumWeight() {
        Double suma = this.listProduccion.stream()
                .mapToDouble(data -> Double.valueOf(data.getPeso()))
                .sum();
        return FORMAT_TWO_DECIMAL.format(suma);
    }

    private String sumBoxes() {
        Integer suma = this.listProduccion.stream()
                .mapToInt(data -> data.getTotalCajas())
                .sum();
        return suma.toString();
    }

    private String sumPallets() {
        return String.valueOf(this.listProduccion.size());
    }

}
