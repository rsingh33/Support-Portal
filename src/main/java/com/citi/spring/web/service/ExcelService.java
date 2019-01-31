package com.citi.spring.web.service;


import com.citi.spring.web.dao.ExcelDao;
import com.citi.spring.web.dao.UsersDao;
import com.citi.spring.web.dao.entity.ExcelRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("excelService")
public class ExcelService {

    @Qualifier("excelDao")
    @Autowired
    private ExcelDao excelDao;
    @Qualifier("usersDao")
    @Autowired
    private UsersDao usersDao;



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
            System.out.println("Error starts here !!");
            e.printStackTrace();
        }
    }

    public  Set<String> getPendingTesters(String releaseName) {
        Set<String> reminderEmailList = new HashSet<>();

        try {
            List<ExcelRow> release = excelDao.getExcelRow(releaseName);

            for (ExcelRow excelRow : release) {

                if (excelRow.getTester() != null && !excelRow.getTester().equals("")) {

                    if (usersDao.existsByName(excelRow.getTester())) {
                        if (excelRow.getStatus() != null && excelRow.getStatus().equals("PENDING")) {
                            String [] emails = usersDao.getEmail(excelRow.getTester()).split(";");
                           for(String email :emails){
                                reminderEmailList.add(email);
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Null pointer exception occurred while getting user's email, " +
                    "please check the user and email list");

            ex.printStackTrace();
            return reminderEmailList;
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


