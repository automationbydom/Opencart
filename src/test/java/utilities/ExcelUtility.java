package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    private XSSFWorkbook openWorkbook() throws IOException {
        FileInputStream fi = new FileInputStream(path);
        return new XSSFWorkbook(fi);
    }

    public int getRowCount(String sheetName) throws IOException {
        try (XSSFWorkbook workbook = openWorkbook()) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
            }
            return sheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (XSSFWorkbook workbook = openWorkbook()) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
            }
            Row row = sheet.getRow(rownum);
            return (row != null) ? row.getLastCellNum() : 0; // Handle null row
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (XSSFWorkbook workbook = openWorkbook()) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
            }
            Row row = sheet.getRow(rownum);
            Cell cell = (row != null) ? row.getCell(colnum) : null; // Handle null row

            DataFormatter formatter = new DataFormatter();
            return (cell != null) ? formatter.formatCellValue(cell) : ""; // Handle null cell
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        File xlfile = new File(path);
        boolean fileExists = xlfile.exists();

        if (!fileExists) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = openWorkbook();
        }

        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }

        Cell cell = row.createCell(colnum);
        cell.setCellValue(data);

        try (FileOutputStream fo = new FileOutputStream(path)) {
            workbook.write(fo);
        }
    }

    public void fillCellColor(String sheetName, int rownum, int colnum, IndexedColors color) throws IOException {
        try (XSSFWorkbook workbook = openWorkbook();
             FileOutputStream fo = new FileOutputStream(path)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
            }

            Row row = sheet.getRow(rownum);
            Cell cell = (row != null) ? row.getCell(colnum) : null; // Handle null row

            if (cell == null) {
                cell = row.createCell(colnum);
            }

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            workbook.write(fo);
        }
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fillCellColor(sheetName, rownum, colnum, IndexedColors.GREEN);
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fillCellColor(sheetName, rownum, colnum, IndexedColors.RED);
    }
}

