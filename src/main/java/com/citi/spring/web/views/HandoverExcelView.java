package com.citi.spring.web.views;


import com.citi.spring.web.dao.entity.Handover;
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
public class HandoverExcelView extends AbstractXlsView {

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"handover.xls\"");

        @SuppressWarnings("unchecked")
        List<Handover> handoverList = (List<Handover>) model.get("handoverList");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Handover");
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
        cell1.setCellValue("Reporter");

        Cell cell2 = header.createCell(1);
        cell2.setCellStyle(style);
        cell2.setCellValue("Email Subject");

        Cell cell3 = header.createCell(2);
        cell3.setCellStyle(style);
        cell3.setCellValue("Jira");

        Cell cell4 = header.createCell(3);
        cell4.setCellStyle(style);
        cell4.setCellValue("Status");

        Cell cell5 = header.createCell(4);
        cell5.setCellStyle(style);
        cell5.setCellValue("Environment");

        Cell cell6 = header.createCell(5);
        cell6.setCellStyle(style);
        cell6.setCellValue("Currenlty With");

        Cell cell8 = header.createCell(6);
        cell8.setCellStyle(style);
        cell8.setCellValue("Last Mod Info");

        Cell cell7 = header.createCell(7);
        cell7.setCellStyle(style);
        cell7.setCellValue("Comments");


        // Create data cells
        int rowCount = 1;
        for (Handover handover : handoverList) {
            Row handoverRow = sheet.createRow(rowCount++);
            handoverRow.createCell(0).setCellValue(handover.getReportedBy());
            handoverRow.createCell(1).setCellValue(handover.getEmailSubject());
            handoverRow.createCell(2).setCellValue(handover.getTracking());
            handoverRow.createCell(3).setCellValue(handover.getStatus().toString());
            handoverRow.createCell(4).setCellValue(handover.getEnvironment().toString());
            handoverRow.createCell(5).setCellValue(handover.getCurrentlyWith().toString());
            handoverRow.createCell(6).setCellValue("Time:   " + sdf.format(handover.getLastMod()) + "  ,    User:  " + handover.getLastModUser());
            handoverRow.createCell(7).setCellValue(handover.getComments());

        }
    }
}