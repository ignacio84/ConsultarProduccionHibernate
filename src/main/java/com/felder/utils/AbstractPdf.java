
package com.felder.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;


abstract class AbstractPdf extends PdfPageEventHelper {
    
    private float cellHeight = 0;

    public void setCellHeight(float cellHeight) {
        this.cellHeight = cellHeight;
    }

    protected PdfPCell getCell(String text,BaseColor backGround, int aligHorizontal, int aligVertical, Font fnt, Integer colspan, Integer rowspan, Integer border) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fnt));
        cell.setPadding(0);
        cell.setBackgroundColor(backGround);
        cell.setHorizontalAlignment(aligHorizontal);
        cell.setVerticalAlignment(aligVertical);
        cell.setBorder(border);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setFixedHeight(this.cellHeight);
        
        return cell;
    }
    
    

    protected PdfPCell getCell(String text, int aligHorizontal, int aligVertical, BaseColor color, Font fnt, Integer border) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fnt));
        cell.setPadding(0);
        cell.setHorizontalAlignment(aligHorizontal);
        cell.setVerticalAlignment(aligVertical);
        cell.setBackgroundColor(color);
//        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorder(border);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setFixedHeight(this.cellHeight);
        return cell;
    }

    protected PdfPCell getCell(String text, int aligHorizontal, int aligVertical, BaseColor color, Font fnt, Integer colspan, Integer rowspan, Integer border) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fnt));
        cell.setPadding(0);
        cell.setHorizontalAlignment(aligHorizontal);
        cell.setVerticalAlignment(aligVertical);
        cell.setBackgroundColor(color);
//        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorder(border);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setFixedHeight(this.cellHeight);
        return cell;
    }

    protected PdfPCell getCell(int aligHorizontal, int aligVertical, BaseColor color, Integer colspan, Integer rowspan, String pathImg, Integer border) throws Exception {
        Image img = Image.getInstance(IOUtils.toByteArray(getClass().getResourceAsStream(pathImg)));
        PdfPCell cell = new PdfPCell(img, true);
        cell.setPadding(0);
        cell.setHorizontalAlignment(aligHorizontal);
        cell.setVerticalAlignment(aligVertical);
        cell.setBackgroundColor(color);
//        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorder(border);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setFixedHeight(this.cellHeight);

        return cell;
    }

    protected PdfPCell getCell(int aligHorizontal, int aligVertical, Integer colspan, Integer rowspan, String pathImg, Integer border) throws Exception {
        Image img = Image.getInstance(IOUtils.toByteArray(getClass().getResourceAsStream(pathImg)));
        PdfPCell cell = new PdfPCell(img, true);
        cell.setPadding(0);
        cell.setHorizontalAlignment(aligHorizontal);
        cell.setVerticalAlignment(aligVertical);
//        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorder(border);
        cell.setRowspan(rowspan);
        cell.setColspan(colspan);
        cell.setFixedHeight(this.cellHeight);
        return cell;
    }

    //AGREGA PIE DE PAGINA CON NUMERO DE PAGINA
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle pageSize = document.getPageSize();
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("Pagina %d", writer.getPageNumber())), (pageSize.getLeft() + pageSize.getRight()) / 2, pageSize.getBottom() + 20, 0);
    }
    
}
