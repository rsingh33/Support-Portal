package com.citi.spring.web.controllers;

import com.citi.spring.web.dao.data.ExcelParser;
import com.citi.spring.web.dao.data.MyCell;
import com.citi.spring.web.dao.data.UATstatus;
import com.citi.spring.web.dao.entity.ExcelRow;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.ExcelService;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class ReleaseController {

    private String fileLocation;

    @Autowired
    private ExcelService excelService;

    @Resource(name = "excelParser")
    private ExcelParser excelParser;
    @Autowired
    private UsersService usersService;
// Start

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"getRelease"})
    public String showReleaseTable(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal) {
        System.out.println("Showing Release table");
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());

        List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());


        if (data1.size() > 0) {
            int pass = 0;
            int fail = 0;
            int pending = 0;
            int total = data1.size();

            for (int i = 0; i < total; i++) {
                if (data1.get(i).getStatus() != null) {
                    if (data1.get(i).getStatus().equals("PASS")) {
                        pass++;
                    }
                    if (data1.get(i).getStatus().equals("FAIL")) {
                        fail++;
                    }
                }
            }

            LocalDate deadline = data1.get(0).getDeadline().toLocalDate();
            LocalDate today = LocalDate.now();
            long daysToDeadline = DAYS.between(today, deadline);
            pass = (pass * 100) / total;
            fail = (fail * 100) / total;
            pending = 100 - (pass + fail);

            List<String> releases = excelService.getReleases();
            model.addAttribute("releases", releases);
            model.addAttribute("deadline", daysToDeadline);
            model.addAttribute("pass", pass);
            model.addAttribute("fail", fail);
            model.addAttribute("pending", pending);
            model.addAttribute("total", total);
        }
        model.addAttribute("data", data1);


        return "releasemanager";
    }

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"downloadReleaseExcel"})
    public ModelAndView getReleaseExcel(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal) {
        List<ExcelRow> releaseList = excelService.getExcel(excelRow.getReleaseName());
        return new ModelAndView("releaseExcelView", "releaseList", releaseList);
    }

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"removeRelease"})
    public String deleteReleaseExcel(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal) {
        excelService.deleteExcel(excelRow.getReleaseName());
        return "redirect:/releasemanager";
    }


    @RequestMapping(value = "/releasemanager")
    public String showReleaseManager(Model model, Principal principal) {


        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        ExcelRow excelRow = new ExcelRow();
        List<String> releases = excelService.getReleases();
        model.addAttribute("releases", releases);
        model.addAttribute("excelRow", excelRow);
        System.out.println(excelRow.toString() + " " + releases.size());
        return "releasemanager";
    }


    @RequestMapping("/releasemanagerform")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("excelRow", new ExcelRow());
        m.addAttribute("uatStatus", UATstatus.values());
        return "releasemanagerform";
    }

    @RequestMapping("/newRelease")
    public String newReleaseform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        return "newRelease";
    }

    @RequestMapping(value = "/releasemanagerform/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        ExcelRow excelRow = excelService.getExcelRow(id);
        m.addAttribute("uatStatus", UATstatus.values());
        m.addAttribute("excelRow", excelRow);
        List<User> userList = usersService.getAllUsers();
        List<String> names = new ArrayList<>();
        for (User user : userList) {
            names.add(user.getName());
        }

        m.addAttribute("userList", names);
//        m.addAttribute("toEdit", true);
//        m.addAttribute("edited", false);
        return "releasemanagerform";
    }

   /* @RequestMapping(value = "/saveRelease", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("excelRow") ExcelRow excelRow, Model m, Principal principal) {
        excelRow.setLastModUser(principal.getName());
        excelService.saveOrUpdate(excelRow);
        m.addAttribute("edited", true);
        m.addAttribute("message","Successfully Saved");

        return "releasemanagerform";
    }*/

    @RequestMapping(value = "/saveRelease", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("excelRow") ExcelRow excelRow, Model m, Principal principal) {
        excelRow.setLastModUser(principal.getName());
        excelService.saveOrUpdate(excelRow);
        System.out.println(excelRow.getReleaseName());
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());

        m.addAttribute("edited", true);
        m.addAttribute("message", "Successfully Saved");


        if (data1.size() > 0) {
            int pass = 0;
            int fail = 0;
            int pending = 0;
            int total = data1.size();

            for (int i = 0; i < total; i++) {
                if (data1.get(i).getStatus() != null) {
                    if (data1.get(i).getStatus().equals("PASS")) {
                        pass++;
                    }
                    if (data1.get(i).getStatus().equals("FAIL")) {
                        fail++;
                    }
                }
            }

            LocalDate deadline = data1.get(0).getDeadline().toLocalDate();
            LocalDate today = LocalDate.now();
            long daysToDeadline = DAYS.between(today, deadline);
            pass = (pass * 100) / total;
            fail = (fail * 100) / total;
            pending = 100 - (pass + fail);

            List<String> releases = excelService.getReleases();
            m.addAttribute("releases", releases);
            m.addAttribute("deadline", daysToDeadline);
            m.addAttribute("pass", pass);
            m.addAttribute("fail", fail);
            m.addAttribute("pending", pending);
            m.addAttribute("total", total);
        }
        m.addAttribute("data", data1);


        return "releasemanager";
    }


    @RequestMapping(value = "/deleteExcelRow/{id}", method = RequestMethod.GET)

    public String delete(@PathVariable int id) {
        System.out.println("In delete " + id);
        excelService.delete(id);
        return "redirect:/releasemanager";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
    public String uploadFile(Model model, MultipartFile file, Principal principal, @RequestParam("releaseName") String releaseName, @RequestParam("deadline")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline) throws IOException {


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
                        excelRow.setLastModUser(principal.getName());
                        excelRow.setReleaseName(releaseName);
                        excelRow.setDeadline(Date.valueOf(deadline));

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

