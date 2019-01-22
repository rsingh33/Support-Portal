package com.citi.spring.web.service;


import com.citi.spring.web.dao.ExcelDao;
import com.citi.spring.web.dao.UsersDao;
import com.citi.spring.web.dao.entity.ExcelRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("excelService")
public class ExcelService {
    private ExcelDao excelDao;
    private UsersDao usersDao;

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

    public List<String> getPendingTesters(String releaseName){
        List<ExcelRow> release =excelDao.getExcelRow(releaseName);
        List <String> reminderEmailList = new ArrayList<>();
        for (ExcelRow excelRow : release){
            if(excelRow.getStatus().equals("PENDING") && excelRow.getTester() != null){
                reminderEmailList.add(usersDao.getEmail(excelRow.getTester()));
            }
        }
        return reminderEmailList;
    }

    public List<String> getReleases() {
        return excelDao.getReleases();
    }

    public boolean deleteExcel(String releaseName) {

        return excelDao.deleteExcel(releaseName);
    }
}


