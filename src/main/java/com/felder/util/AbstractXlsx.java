package com.felder.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

abstract class AbstractXlsx {

    protected Workbook workBook;
    protected DataFormat dataFormat;
    protected Row row;

    protected CellStyle cellStyleTitle() {
        // Creamos el estilo paga las celdas del encabezado
        CellStyle cellStyleTitle = this.workBook.createCellStyle();
        cellStyleTitle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());// Indicamos que tendra un fondo gris al 50%
        cellStyleTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);//fondo dolido
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);//alineacion centrada
        //agrega bordes
        cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        return cellStyleTitle;
    }

    protected CellStyle cellStyleContent(String format) {
        CellStyle cellStyleContent = this.workBook.createCellStyle();
        if (format != null) {
            cellStyleContent.setDataFormat(this.dataFormat.getFormat(format));
        }
        cellStyleContent.setWrapText(true);//auto aguste de celda
        cellStyleContent.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        return cellStyleContent;
    }

    protected void addCell(Integer columnNum, String value, CellStyle cellStyle) {
        Cell cell;
        cell = row.createCell(columnNum);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);

    }

    protected void addCell(Integer columnNum, Double value, CellStyle cellStyle) {
        Cell cell;
        cell = row.createCell(columnNum);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    protected void addCell(Integer columnNum, Integer value, CellStyle cellStyle) {
        Cell cell;
        cell = row.createCell(columnNum);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    protected void addCellFormula(Integer columnNum, String formula, CellStyle cellStyle) {
        Cell cell;
        cell = row.createCell(columnNum);
        cell.setCellStyle(cellStyle);
        cell.setCellType(Cell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
    }

    protected void autoSizeColumn(Sheet sheet, Integer[] numColumns) {
        for (int i = 0; i < numColumns.length; i++) {
            sheet.autoSizeColumn(numColumns[i]);
        }
    }

}
