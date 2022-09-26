package com.xxx.example.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 将数据写入Excel表
 */
public class WriteDataToExcel {
    public static void main(String[] args) throws Exception {

        // Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

        // Create row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> empinfo = new TreeMap<>();
        empinfo.put("1", new Object[] { "编号", "姓名", "描述" });

        empinfo.put("2", new Object[] { "10010", "李立", "技术经理" });

        empinfo.put("3", new Object[] { "10011", "张晓龙", "微信它爹" });

        empinfo.put("4", new Object[] { "10012", "王小飞", "技术作家" });

        empinfo.put("5", new Object[] { "10013", "库里", "NBA球员" });

        empinfo.put("6", new Object[] { "10014", "李双双", "体操运动员" });

        // Iterate over data and write to sheet
        Set<String> keyid = empinfo.keySet();
        int rowid = 0;

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = empinfo.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        // Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File("/tmp/excel/Writesheet.xlsx"));

        workbook.write(out);
        out.close();
        System.out.println("Writesheet.xlsx written successfully");
    }
}
