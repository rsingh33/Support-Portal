package com.citi.spring.web.views;


import com.citi.spring.web.dao.entity.ExcelRow;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Component
public class TemplateExcelView extends AbstractXlsView {

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"template.xls\"");



        // create excel xls sheet
        Sheet sheet = workbook.createSheet("SignOff Tracker");
        CreationHelper createHelper = workbook.getCreationHelper();


        CellStyle style = workbook.createCellStyle();


        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLUE1.getIndex());
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontName("Calibri");
        font.setFontHeight((short)300);
        style.setFont(font);

        // create header row
       
        Row header = sheet.createRow(0);


        Cell cell1 = header.createCell(0);
        cell1.setCellStyle(style);
        cell1.setCellValue("Key");

        Cell cell2 = header.createCell(1);
        cell2.setCellStyle(style);
        cell2.setCellValue("Summary");

        Cell cell3 = header.createCell(2);
        cell3.setCellStyle(style);
        cell3.setCellValue("Engineer");

        Cell cell4 = header.createCell(3);
        cell4.setCellStyle(style);
        cell4.setCellValue("Script Location");

        Cell cell5 = header.createCell(4);
        cell5.setCellStyle(style);
        cell5.setCellValue("UAT Tester");

        Cell cell6 = header.createCell(5);
        cell6.setCellStyle(style);
        cell6.setCellValue("UAT Status");

        Cell cell8 = header.createCell(6);
        cell8.setCellStyle(style);
        cell8.setCellValue("Comments");



    }
}