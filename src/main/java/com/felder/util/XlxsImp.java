package com.felder.util;

import com.felder.model.pojo.Produccion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class XlxsImp extends AbstractXlsx implements IXlsx {

    private static final String STR_DATE_FORMAT = "dd-MM-yyyy";
    private static final String STR_HOUR_FORMAT = "hh:mm:ss";
    private static final String STR_DECIMAL_FORMAT = "###,###,##0.00";

    private static final Integer INT_ROW_START = 2;
    private static final Integer INT_COLUMN_START = 2;

    private List<Produccion> listProduccion;
    private Integer column_;
    private Sheet sheet;

    @Override
    public void makeFromProduccion(List<Produccion> listProduccion, String path) throws Exception {
        path += ".XLSX";
        this.listProduccion = listProduccion;
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
        this.addCell(column_++, "FECHA", this.cellStyleTitle());
        this.addCell(column_++, "HORA", this.cellStyleTitle());
        this.addCell(column_++, "MARBETE", this.cellStyleTitle());
        this.addCell(column_++, "PRODUCTO", this.cellStyleTitle());
        this.addCell(column_++, "DESCRIPCIÓN", this.cellStyleTitle());
        this.addCell(column_++, "CARGA", this.cellStyleTitle());
        this.addCell(column_++, "CAJAS", this.cellStyleTitle());
        this.addCell(column_++, "PESO", this.cellStyleTitle());
        this.addCell(column_++, "RESPONSABLE", this.cellStyleTitle());
    }

    private void renderContent() {
        column_ = INT_COLUMN_START;
        //contenido de la tabla
        for (Produccion produccion : listProduccion) {
            this.row = sheet.createRow(this.row.getRowNum() + 1);
            this.addCell(column_++, produccion.getFecha(), this.cellStyleContent(STR_DATE_FORMAT));
            this.addCell(column_++, produccion.getHora(), this.cellStyleContent(STR_HOUR_FORMAT));
            this.addCell(column_++, produccion.getFolio(), this.cellStyleContent(null));
            this.addCell(column_++, produccion.getCodigoProducto(), this.cellStyleContent(null));
            this.addCell(column_++, produccion.getDescripcion(), this.cellStyleContent(null));
            this.addCell(column_++, produccion.getCarga(), this.cellStyleContent(null));
            this.addCell(column_++, produccion.getTotalCajas(), this.cellStyleContent(null));
            this.addCell(column_++, Double.valueOf(produccion.getPeso()), this.cellStyleContent(STR_DECIMAL_FORMAT));
            this.addCell(column_++, produccion.getResponsable(), this.cellStyleContent(null));
            column_ = INT_COLUMN_START;
        }
    }

    private void renderTotal() {
        this.row = sheet.createRow(this.row.getRowNum() + 1);
        this.addCellFormula(8, "SUM(I" + (INT_ROW_START + 2) + ":I" + (INT_ROW_START + 1 + listProduccion.size()) + ")", this.cellStyleContent(STR_DECIMAL_FORMAT));
        this.addCellFormula(9, "SUM(J" + (INT_ROW_START + 2) + ":J" + (INT_ROW_START + 1 + listProduccion.size()) + ")", this.cellStyleContent(STR_DECIMAL_FORMAT));

    }

}
