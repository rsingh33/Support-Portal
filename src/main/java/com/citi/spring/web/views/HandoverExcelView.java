package com.citi.spring.web.views;



import com.citi.spring.web.dao.entity.Handover;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

@Component
public class HandoverExcelView extends AbstractXlsView {



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
        Sheet sheet = workbook.createSheet("Handover"  );
        CreationHelper createHelper = workbook.getCreationHelper();

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);


        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Reporter");
        header.createCell(1).setCellValue("Email Subject");
        header.createCell(2).setCellValue("Jira");
        header.createCell(3).setCellValue("Status");
        header.createCell(4).setCellValue("Environment");
        header.createCell(5).setCellValue("Currenlty With");
        header.createCell(6).setCellValue("Last Modified");
        header.createCell(7).setCellValue("Comments");
        // Create data cells
        int rowCount = 1;
        for (Handover handover : handoverList){
            Row handoverRow = sheet.createRow(rowCount++);
            handoverRow.createCell(0).setCellValue(handover.getReportedBy());
            handoverRow.createCell(1).setCellValue(handover.getEmailSubject());
            handoverRow.createCell(2).setCellValue(handover.getTracking());
            handoverRow.createCell(3).setCellValue(handover.getStatus().toString());
            handoverRow.createCell(4).setCellValue(handover.getEnvironment().toString());
            handoverRow.createCell(5).setCellValue(handover.getCurrentlyWith().toString());
            handoverRow.createCell(6).setCellValue(handover.getLastMod());
            handoverRow.createCell(7).setCellValue(handover.getComments());

        }
    }
}