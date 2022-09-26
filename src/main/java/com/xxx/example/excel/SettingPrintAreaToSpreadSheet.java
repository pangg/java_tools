package com.xxx.example.excel;

import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 设置电子表格的打印区域
 */
public class SettingPrintAreaToSpreadSheet {
    public static void main(String[] args) throws Exception {

        // Create a Work Book
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Print Area");

        // set print area with indexes
        workbook.setPrintArea(0, // sheet index
                0, // start column
                5, // end column
                0, // start row
                5 // end row
        );

        // set paper size
        spreadsheet.getPrintSetup().setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);

        // set display grid lines or not
        spreadsheet.setDisplayGridlines(true);

        // set print grid lines or not
        spreadsheet.setPrintGridlines(true);
        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/printarea.xlsx"));

        workbook.write(out);
        out.close();

        System.out.println("printarea.xlsx written successfully");
    }
}
