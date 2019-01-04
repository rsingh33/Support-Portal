package com.citi.spring.web.service;


import com.citi.spring.web.dao.ExcelDao;
import com.citi.spring.web.dao.entity.ExcelRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("excelService")
public class ExcelService {
    private ExcelDao excelDao;

    @Autowired
    public void setoffersdao(ExcelDao excelDao) {
        this.excelDao = excelDao;
    }

    public List<ExcelRow> getExcel() {
        return excelDao.getExcelRow();
    }


    public ExcelRow getExcelRow(int id) {
        if (id == 0) return null;
        ExcelRow excelRow = excelDao.getExcelRow(id);

        return excelRow;
    }

    public void saveOrUpdate(ExcelRow excelRow) {

        excelDao.saveOrUpdateExcelRow(excelRow);

    }

    public void delete(int id) {
        excelDao.deleteExcelRow(id);
    }

    public void saveorUpdateAll(List<ExcelRow> list) {

        try {
            excelDao.saveorUpdateAll(list);
        } catch (Exception e) {
            System.out.println("Error starts here !!1");
            e.printStackTrace();
        }
    }
}

