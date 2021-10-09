package com.felder.utils;

import com.felder.model.entity.Pallet;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxImplBoxes extends AbstractXlsx implements IXlsx<List<Pallet>, String> {

    private static final String STR_DATE_FORMAT = "dd-MM-yyyy";
    private static final String STR_HOUR_FORMAT = "hh:mm:ss";
    private static final String STR_DECIMAL_FORMAT = "###,###,##0.00";

    private static final Integer INT_ROW_START = 2;
    private static final Integer INT_COLUMN_START = 2;

    private List<Pallet> listPallet;
    private Integer column_;
    private Sheet sheet;

    @Override
    public void make(List<Pallet> pallets, String path) throws Exception {
        path += ".XLSX";
        this.listPallet = pallets;
        this.column_ = INT_COLUMN_START;
        this.workBook = new XSSFWorkbook();
        this.dataFormat = workBook.createDataFormat();

        this.sheet = this.workBook.createSheet("PRODUCCIÓN");
        this.renderTitle();
        this.renderContent();
        this.renderTotal();
        //autoajuste para columnas
        this.autoSizeColumn(sheet, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10});

        FileOutputStream salida = new FileOutputStream(path);
        this.workBook.write(salida);
        this.workBook.close();
        Desktop.getDesktop().open(new File(path));
    }

    private void renderTitle() {
        //se crea una nueva fila
        this.row = sheet.createRow(INT_ROW_START);
        //se crea titulos de la tabla
        this.addCell(column_++, "CODIGO DE BARRAS", this.cellStyleTitle());
        this.addCell(column_++, "FECHA", this.cellStyleTitle());
        this.addCell(column_++, "HORA", this.cellStyleTitle());
        this.addCell(column_++, "MARBETE", this.cellStyleTitle());
        this.addCell(column_++, "PRODUCTO", this.cellStyleTitle());
        this.addCell(column_++, "DESCRIPCIÓN", this.cellStyleTitle());
        this.addCell(column_++, "LOTE", this.cellStyleTitle());
        this.addCell(column_++, "PESO", this.cellStyleTitle());
        this.addCell(column_++, "RESPONSABLE", this.cellStyleTitle());
    }

    private void renderContent() {
        column_ = INT_COLUMN_START;
        this.listPallet
                .stream()
                .forEach((pallet) -> {
                    pallet.getLines()
                            .stream()
                            .forEach((box) -> {
                                this.row = sheet.createRow(this.row.getRowNum() + 1);
                                this.addCell(column_++, box.getCodigoBarras(), this.cellStyleContent(STR_DATE_FORMAT));
                                this.addCell(column_++, pallet.getFecha().toLocalDate().toString(), this.cellStyleContent(STR_DATE_FORMAT));
                                this.addCell(column_++, pallet.getFecha().toLocalTime().toString(), this.cellStyleContent(STR_HOUR_FORMAT));
                                this.addCell(column_++, pallet.getFolio(), this.cellStyleContent(null));
                                this.addCell(column_++, pallet.getCodigoProducto(), this.cellStyleContent(null));
                                this.addCell(column_++, pallet.getLines().get(0).getDescripcion(), this.cellStyleContent(null));
                                this.addCell(column_++, pallet.getLote(), this.cellStyleContent(null));
                                this.addCell(column_++, Double.valueOf(box.getPeso()), this.cellStyleContent(STR_DECIMAL_FORMAT));
                                this.addCell(column_++, pallet.getResponsable(), this.cellStyleContent(null));
                                column_ = INT_COLUMN_START;
                            });
                });
    }

    private void renderTotal() {
        this.row = sheet.createRow(this.row.getRowNum() + 1);
        this.addCellFormula(8, "SUM(I" + (INT_ROW_START + 2) + ":I" + (INT_ROW_START + 1 + this.row.getRowNum()-(INT_ROW_START+1)) + ")", this.cellStyleContent(STR_DECIMAL_FORMAT));
        this.addCellFormula(9, "SUM(J" + (INT_ROW_START + 2) + ":J" + (INT_ROW_START + 1 + this.row.getRowNum()-(INT_ROW_START+1)) + ")", this.cellStyleContent(STR_DECIMAL_FORMAT));
    }

}
