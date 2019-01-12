package com.citi.spring.web.controllers;

import com.citi.spring.web.dao.data.ExcelParser;
import com.citi.spring.web.dao.data.MyCell;
import com.citi.spring.web.dao.entity.ExcelRow;
import com.citi.spring.web.service.ExcelService;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ReleaseController {

    private String fileLocation;

    @Autowired
    private ExcelService excelService;

    @Resource(name = "excelParser")
    private ExcelParser excelParser;
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/releasemanager")
    public String showReleaseManager(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());

        List<ExcelRow> data1 = excelService.getExcel();
        model.addAttribute("data", data1);

        return "releasemanager";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
    public String uploadFile(Model model, MultipartFile file, Principal principal) throws IOException {
        try {
            if (principal != null)
                model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
            InputStream in = file.getInputStream();
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
            FileOutputStream f = new FileOutputStream(fileLocation);
            int ch = 0;
            while ((ch = in.read()) != -1) {
                f.write(ch);
            }
            f.flush();
            f.close();

            if (fileLocation != null) {
                if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
                    Map<Integer, List<MyCell>> data = excelParser.readExcel(fileLocation);
                    List<ExcelRow> excel = new ArrayList<>();

                    int i = 0;
                    for (Map.Entry<Integer, List<MyCell>> entry : data.entrySet()) {
                        if (i == 0) {
                            i++;
                            continue;
                        }
                        ExcelRow excelRow = new ExcelRow();
                        excelRow.setJiraKey(entry.getValue().get(0).getContent());
                        excelRow.setSummary(entry.getValue().get(1).getContent());
                        excelRow.setEngineer(entry.getValue().get(2).getContent());
                        excelRow.setScriptLocation(entry.getValue().get(3).getContent());
                        excelRow.setTester(entry.getValue().get(4).getContent());
                        excelRow.setStatus(entry.getValue().get(5).getContent());
                        excelRow.setComments(entry.getValue().get(6).getContent());

                        excel.add(excelRow);
                    }

                    excelService.saveorUpdateAll(excel);


                } else {
                    model.addAttribute("message", "Not a valid excel file!");
                }
            } else {
                model.addAttribute("message", "File missing! Please upload an excel file.");
            }
            model.addAttribute("releaseName", excelParser.getReleaseName(fileLocation));
            model.addAttribute("message", "File: " + file.getOriginalFilename()
                    + " has been uploaded and processed successfully!");
        } catch (FileNotFoundException f) {
            System.out.println("File not found Exception occured");
            model.addAttribute("message", "File missing! Please upload an excel file.");
        }
        return "redirect:/releasemanager";
    }

}

