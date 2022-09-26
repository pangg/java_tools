package com.xxx.example.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * 在电子表格中创建不同类型的单元格
 */
public class TypesOfCellsInExcel {
    public static void main(String[] args)throws Exception {

        //Creating a Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Creating a Spread sheet
        XSSFSheet spreadsheet = workbook.createSheet("单元格类型");
        XSSFRow row = spreadsheet.createRow((short) 2);

        row.createCell(0).setCellValue("单元格类型");
        row.createCell(1).setCellValue("单元格值");
        row = spreadsheet.createRow((short) 3);

        row.createCell(0).setCellValue("set cell type BLANK");
        row.createCell(1);
        row = spreadsheet.createRow((short) 4);

        row.createCell(0).setCellValue("set cell type BOOLEAN");
        row.createCell(1).setCellValue(true);
        row = spreadsheet.createRow((short) 5);

        row.createCell(0).setCellValue("set cell type ERROR");
        row.createCell(1).setCellValue(XSSFCell.CELL_TYPE_ERROR );
        row = spreadsheet.createRow((short) 6);

        row.createCell(0).setCellValue("set cell type date");
        row.createCell(1).setCellValue(new Date());
        row = spreadsheet.createRow((short) 7);

        row.createCell(0).setCellValue("set cell type numeric" );
        row.createCell(1).setCellValue(20 );
        row = spreadsheet.createRow((short) 8);

        row.createCell(0).setCellValue("set cell type string");
        row.createCell(1).setCellValue("A String");

        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/typesofcells.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("typesofcells.xlsx written successfully");
    }
}
