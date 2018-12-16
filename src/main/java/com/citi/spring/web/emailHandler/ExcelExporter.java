package com.citi.spring.web.emailHandler;



import com.citi.spring.web.dao.entity.Handover;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    private static String[] columns = {"Reported By", "Email", "Tracking", "Comments", "Last Modified", "Status", "Environment", "Currently With"};


    public static void export(List<Handover> handoverRows) throws InvalidFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Handover");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);


        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);


        // Create a Row
        Row headerRow = sheet.createRow(0);


        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (Handover handoverRow : handoverRows) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(handoverRow.getReportedBy());

            row.createCell(1)
                    .setCellValue(handoverRow.getEmailSubject());

            row.createCell(2)
                    .setCellValue(handoverRow.getTracking());
            row.createCell(3)
                    .setCellValue(handoverRow.getComments());


            Cell lastModCell = row.createCell(4);
            lastModCell.setCellValue(handoverRow.getLastMod());
            lastModCell.setCellStyle(dateCellStyle);

            row.createCell(5)
                    .setCellValue(handoverRow.getStatus().toString());
            row.createCell(6)
                    .setCellValue(handoverRow.getEnvironment().toString());
            row.createCell(7)
                    .setCellValue(handoverRow.getCurrentlyWith().toString());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("handover.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}