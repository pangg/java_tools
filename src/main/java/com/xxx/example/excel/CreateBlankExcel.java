package com.xxx.example.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 创建空Excel表
 */
public class CreateBlankExcel {
    public static void main(String[] args) throws Exception {

        // Create Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create file system using specific name
        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/createBlankWorkBook.xlsx"));

        // write operation workbook using file out object
        workbook.write(out);
        out.close();
        System.out.println("createworkbook.xlsx written successfully");
    }

}
