package com.felder.utils;

import com.felder.model.entity.Pallet;
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
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

public class PdfImplBoxes extends AbstractPdf implements IPdf<List<Pallet>, String> {

//    private static final float FLT_SIZECOLUMNS[] = new float[]{25, 20, 25, 20, 100, 30, 20, 25, 25};
    private static final float FLT_SIZECOLUMNS[] = new float[]{45, 20, 25, 20, 20, 90, 25, 25, 35};
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
    private List<Pallet> listPallets;
    private Document document;
    private PdfWriter writer;

    @Override
    public void make(List<Pallet> pallets, String path) throws Exception {
        path += ".PDF";
        this.listPallets = pallets;
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
        this.table.addCell(this.getCell("CODIGO DE BARRAS", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("FECHA", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("HORA", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("MARBETE", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("CODIGO PRODUCTO", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("DESCRIPCIÒN", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("LOTE", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("PESO", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        this.table.addCell(this.getCell("RESPONSABLE", BACKGROUND_COLOR_TITLE, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
    }

    private void renderContent() {
        this.listPallets
                .stream()
                .forEach((pallet) -> {
                    pallet.getLines()
                            .stream()
                            .forEach((box) -> {
                                table.addCell(this.getCell(box.getCodigoBarras(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getFecha().toLocalDate().toString(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getFecha().toLocalTime().toString(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getFolio().toString(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getCodigoProducto(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getLines().get(0).getDescripcion(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getLote(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(FORMAT_TWO_DECIMAL.format(Double.valueOf(box.getPeso())), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                                table.addCell(this.getCell(pallet.getResponsable(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_CONTENT, 1, 1, 15));
                            });
                });
    }

    private void renderTotal() {
        table.addCell(this.getCell("TOTAL :  ", BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_RIGHT, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 6, 1, 0));
        table.addCell(this.getCell(this.sumBoxes(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        table.addCell(this.getCell(this.sumWeight(), BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 15));
        table.addCell(this.getCell("", BACKGROUND_COLOR_CONTENT, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, FNT_TABLE_TITLE, 1, 1, 0));
    }

    private String sumWeight() {
        Double suma = this.listPallets.stream()
                .mapToDouble(data -> Double.valueOf(data.getPeso()))
                .sum();
        return FORMAT_TWO_DECIMAL.format(suma);
    }

    private String sumBoxes() {

        Integer suma = this.listPallets.stream()
                .mapToInt(data -> data.getNumeroCajas())
                .sum();
        return suma.toString();
    }

    private String sumPallets() {
        return String.valueOf(this.listPallets.size());
    }

}
