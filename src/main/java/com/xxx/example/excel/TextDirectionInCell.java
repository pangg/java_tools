package com.xxx.example.excel;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 设置单元格中的文本的方向
 */
public class TextDirectionInCell {
    public static void main(String[] args) throws Exception {

        // Creating Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Creating a Spread Sheet
        XSSFSheet spreadsheet = workbook.createSheet("Text direction");
        XSSFRow row = spreadsheet.createRow(2);
        XSSFCellStyle myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 0);
        XSSFCell cell = row.createCell(1);

        cell.setCellValue("0D angle");
        cell.setCellStyle(myStyle);

        // 30 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 30);

        cell = row.createCell(3);
        cell.setCellValue("30D angle");
        cell.setCellStyle(myStyle);

        // 90 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 90);

        cell = row.createCell(5);
        cell.setCellValue("90D angle");
        cell.setCellStyle(myStyle);

        // 120 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 120);

        cell = row.createCell(7);
        cell.setCellValue("120D angle");
        cell.setCellStyle(myStyle);

        // 270 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 270);
        cell = row.createCell(9);
        cell.setCellValue("270D angle");
        cell.setCellStyle(myStyle);

        // 360 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 360);

        cell = row.createCell(12);
        cell.setCellValue("360D angle");
        cell.setCellStyle(myStyle);
        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/textdirection.xlsx"));

        workbook.write(out);
        out.close();

        System.out.println("textdirection.xlsx written successfully");
    }
}
