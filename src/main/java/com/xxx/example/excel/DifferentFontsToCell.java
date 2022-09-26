package com.xxx.example.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 将不同样式应用于Excel中的单元格
 */
public class DifferentFontsToCell {
    public static void main(String[] args) throws Exception {

        // Create a Work Book
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a Spread Sheet
        XSSFSheet spreadsheet = workbook.createSheet("Fontstyle");
        XSSFRow row = spreadsheet.createRow(2);

        // Create a new font and alter it
        XSSFFont font = workbook.createFont();

        font.setFontHeightInPoints((short) 30);
        font.setFontName("IMPACT");
        font.setItalic(true);
        font.setColor(HSSFColor.BRIGHT_GREEN.index);

        // Set font into style
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        // Create a cell with a value and set style to it.
        XSSFCell cell = row.createCell(1);

        cell.setCellValue("文字样式~");
        cell.setCellStyle(style);

        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/fontstyle.xlsx"));
        workbook.write(out);
        out.close();

        System.out.println("fontstyle.xlsx written successfully");
    }
}
