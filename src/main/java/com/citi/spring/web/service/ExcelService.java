package com.citi.spring.web.service;


import com.citi.spring.web.dao.ExcelDao;
import com.citi.spring.web.dao.entity.ExcelRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("excelService")
public class ExcelService {
    private ExcelDao excelDao;

    @Autowired
    public void setoffersdao(@Qualifier("excelDao") ExcelDao excelDao) {
        this.excelDao = excelDao;
    }

    public List<ExcelRow> getExcel(String releaseName) {
        return excelDao.getExcelRow(releaseName);
    }

//    public List<ExcelRow> getCurrentRelease() {
//        return excelDao.getExcelRow();
//    }


    public ExcelRow getExcelRow(int id) {
        if (id == 0) return null;
        ExcelRow excelRow = excelDao.getExcelRow(id);

        return excelRow;
    }

    public void saveOrUpdate(ExcelRow excelRow) {

        excelDao.saveOrUpdateExcelRow(excelRow);

    }

    public void delete(int id) {

        System.out.println("In excel service delete");
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

    public List<String> getReleases() {
        return excelDao.getReleases();
    }
}


