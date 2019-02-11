package com.citi.spring.web.dao.data;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class ExcelParser {

    public Map<Integer, List<MyCell>> readExcel(String fileLocation) throws Exception {

        Map<Integer, List<MyCell>> data = new HashMap<>();
        FileInputStream fis = new FileInputStream(new File(fileLocation));

        if (fileLocation.endsWith(".xls")) {
            data = readHSSFWorkbook(fis);
        } else if (fileLocation.endsWith(".xlsx")) {
            data = readXSSFWorkbook(fis);
        }

        int maxNrCols = data.values().stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        data.values().stream()
                .filter(ls -> ls.size() < maxNrCols)
                .forEach(ls -> {
                    IntStream.range(ls.size(), maxNrCols)
                            .forEach(i -> ls.add(new MyCell("")));
                });

        return data;
    }

    private String readCellContent(Cell cell) {
        String content;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                content = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    content = cell.getDateCellValue() + "";
                } else {
                    content = cell.getNumericCellValue() + "";
                }
                break;
            case BOOLEAN:
                content = cell.getBooleanCellValue() + "";
                break;
            case FORMULA:
                content = cell.getCellFormula() + "";
                break;
            default:
                content = "";
        }
        return content;
    }

    private Map<Integer, List<MyCell>> readHSSFWorkbook(FileInputStream fis) throws IOException {
        Map<Integer, List<MyCell>> data = new HashMap<>();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fis);

            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                data.put(i, new ArrayList<>());
                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            HSSFCellStyle cellStyle = cell.getCellStyle();

                            MyCell myCell = new MyCell();

                            HSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                            if (bgColor != null) {
                                short[] rgbColor = bgColor.getTriplet();
                                myCell.setBgColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                            }
                            HSSFFont font = cell.getCellStyle()
                                    .getFont(workbook);
                            myCell.setTextSize(font.getFontHeightInPoints() + "");
                            if (font.getBold()) {
                                myCell.setTextWeight("bold");
                            }
                            HSSFColor textColor = font.getHSSFColor(workbook);
                            if (textColor != null) {
                                short[] rgbColor = textColor.getTriplet();
                                myCell.setTextColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                            }
                            myCell.setContent(readCellContent(cell));
                            data.get(i)
                                    .add(myCell);
                        } else {
                            data.get(i)
                                    .add(new MyCell(""));
                        }
                    }
                }
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return data;
    }

    public String getReleaseName(String fileLocation) throws IOException {
        FileInputStream fis = new FileInputStream(new File(fileLocation));
        XSSFWorkbook workbook = null;
        String sheetName;
        try {
            workbook = new XSSFWorkbook(fis);

            sheetName = workbook.getSheetName(0);
            System.out.println("Sheet Name is : " + sheetName);
        } finally {
            if (workbook != null) {
                workbook.close();
            }

        }
        return sheetName;
    }
        private Map<Integer, List<MyCell>> readXSSFWorkbook (FileInputStream fis) throws Exception {
            XSSFWorkbook workbook = null;
            Map<Integer, List<MyCell>> data = new HashMap<>();
            try {
                workbook = new XSSFWorkbook(fis);
                int totalSheets = workbook.getNumberOfSheets();
                int totalRows = 0;
                String sheetName = workbook.getSheetName(0);



                for (int sheetNumber = 0; sheetNumber < totalSheets; sheetNumber++) {

                    XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

                    for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {

                        XSSFRow row = sheet.getRow(i);
                        data.put(totalRows, new ArrayList<>());
                        if (row != null) {
                            for (int j = 0; j < row.getLastCellNum(); j++) {

                                XSSFCell cell = row.getCell(j);
                                if (cell != null) {

                                    XSSFCellStyle cellStyle = cell.getCellStyle();

                                    MyCell myCell = new MyCell();
                                    XSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                                    if (bgColor != null) {
                                        byte[] rgbColor = bgColor.getRGB();
                                        myCell.setBgColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," +
                                                (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                                    }
                                    XSSFFont font = cellStyle.getFont();
                                    myCell.setTextSize(font.getFontHeightInPoints() + "");
                                    if (font.getBold()) {
                                        myCell.setTextWeight("bold");
                                    }
                                    XSSFColor textColor = font.getXSSFColor();
                                    if (textColor != null) {
                                        byte[] rgbColor = textColor.getRGB();
                                        myCell.setTextColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," + (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                                    }
                                    myCell.setContent(readCellContent(cell));
                                    data.get(totalRows).add(myCell);
                                } else {
                                    data.get(totalRows).add(new MyCell(""));
                                }
                            }
                        }
                        totalRows++;
                    }
                }
            } finally {
                if (workbook != null) {
                    workbook.close();
                }
            }
            return data;
        }

        public Map<Integer, Map<Integer, List<MyCell>>> readXSSFWorkbookAsSheets (FileInputStream fis) throws
        IOException {
            XSSFWorkbook workbook = null;
            Map<Integer, Map<Integer, List<MyCell>>> excel = new HashMap<>();
            try {
                workbook = new XSSFWorkbook(fis);
                int totalSheets = workbook.getNumberOfSheets();

                for (int sheetNumber = 0; sheetNumber < totalSheets; sheetNumber++) {

                    XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
                    Map<Integer, List<MyCell>> excelSheet = new HashMap<>();
                    excel.put(sheetNumber, excelSheet);
                    for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {

                        XSSFRow row = sheet.getRow(i);
                        excelSheet.put(i, new ArrayList<>());
                        if (row != null) {
                            for (int j = 0; j < row.getLastCellNum(); j++) {

                                XSSFCell cell = row.getCell(j);
                                if (cell != null) {

                                    XSSFCellStyle cellStyle = cell.getCellStyle();

                                    MyCell myCell = new MyCell();
                                    XSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                                    if (bgColor != null) {
                                        byte[] rgbColor = bgColor.getRGB();
                                        myCell.setBgColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," +
                                                (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                                    }
                                    XSSFFont font = cellStyle.getFont();
                                    myCell.setTextSize(font.getFontHeightInPoints() + "");
                                    if (font.getBold()) {
                                        myCell.setTextWeight("bold");
                                    }
                                    XSSFColor textColor = font.getXSSFColor();
                                    if (textColor != null) {
                                        byte[] rgbColor = textColor.getRGB();
                                        myCell.setTextColor("rgb(" + (rgbColor[0] < 0 ? (rgbColor[0] + 0xff) : rgbColor[0]) + "," + (rgbColor[1] < 0 ? (rgbColor[1] + 0xff) : rgbColor[1]) + "," + (rgbColor[2] < 0 ? (rgbColor[2] + 0xff) : rgbColor[2]) + ")");
                                    }
                                    myCell.setContent(readCellContent(cell));
                                    excelSheet.get(i).add(myCell);
                                } else {
                                    excelSheet.get(i).add(new MyCell(""));
                                }
                            }
                        }

                    }

                    excel.put(sheetNumber, excelSheet);
                }
            } finally {
                if (workbook != null) {
                    workbook.close();
                }
            }
            return excel;
        }

        private Map<Integer, Map<Integer, List<MyCell>>> readHSSFWorkbookBySheets (FileInputStream fis) throws
        IOException {
            Map<Integer, Map<Integer, List<MyCell>>> excel = new HashMap<>();
            HSSFWorkbook workbook = null;
            try {
                workbook = new HSSFWorkbook(fis);
                int totalSheets = workbook.getNumberOfSheets();

                for (int sheetNumber = 0; sheetNumber < totalSheets; sheetNumber++) {

                    HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
                    Map<Integer, List<MyCell>> excelSheet = new HashMap<>();
                    excel.put(sheetNumber, excelSheet);
                    for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                        HSSFRow row = sheet.getRow(i);
                        excelSheet.put(i, new ArrayList<>());
                        if (row != null) {
                            for (int j = 0; j < row.getLastCellNum(); j++) {
                                HSSFCell cell = row.getCell(j);
                                if (cell != null) {
                                    HSSFCellStyle cellStyle = cell.getCellStyle();

                                    MyCell myCell = new MyCell();

                                    HSSFColor bgColor = cellStyle.getFillForegroundColorColor();
                                    if (bgColor != null) {
                                        short[] rgbColor = bgColor.getTriplet();
                                        myCell.setBgColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                                    }
                                    HSSFFont font = cell.getCellStyle()
                                            .getFont(workbook);
                                    myCell.setTextSize(font.getFontHeightInPoints() + "");
                                    if (font.getBold()) {
                                        myCell.setTextWeight("bold");
                                    }
                                    HSSFColor textColor = font.getHSSFColor(workbook);
                                    if (textColor != null) {
                                        short[] rgbColor = textColor.getTriplet();
                                        myCell.setTextColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
                                    }
                                    myCell.setContent(readCellContent(cell));
                                    excelSheet.get(i)
                                            .add(myCell);
                                } else {
                                    excelSheet.get(i)
                                            .add(new MyCell(""));
                                }
                            }
                        }
                    }
                    excel.put(sheetNumber, excelSheet);
                }
            } finally {
                if (workbook != null) {
                    workbook.close();
                }
            }
            return excel;
        }

    }

