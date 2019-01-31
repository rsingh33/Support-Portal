package com.citi.spring.web.controllers;

import com.citi.spring.web.dao.data.ExcelParser;
import com.citi.spring.web.dao.data.MyCell;
import com.citi.spring.web.dao.data.UATstatus;
import com.citi.spring.web.dao.entity.ExcelRow;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.EmailService;
import com.citi.spring.web.service.ExcelService;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.*;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    private EmailService emailService;
// Start

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"getRelease"})
    public String showReleaseTable(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal,  RedirectAttributes redirectAttributes) {
        System.out.println("Showing Release table");
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());

        List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());

        dataPopulate(data1, model);

        return "releasemanager";
    }

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"downloadReleaseExcel"})
    public ModelAndView getReleaseExcel(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal,RedirectAttributes redirectAttributes) {

        List<ExcelRow> releaseList = excelService.getExcel(excelRow.getReleaseName());
        return new ModelAndView("releaseExcelView", "releaseList", releaseList);
    }

    @RequestMapping(value = "/downloadTemplate")
    public ModelAndView getReleaseTemplate() {
        return new ModelAndView("templateExcelView");
    }

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"sendReminder"})
    public String sendReminder(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        System.out.println("Reminder Email sent triggered");

        Set<String> toEmailList = excelService.getPendingTesters(excelRow.getReleaseName());
        if (toEmailList.isEmpty()) {
            redirectAttributes.addFlashAttribute("warning", "There are no valid user to email mappings found.  ");
            return "redirect:/releasemanager";
        }
        String content = "Hi," +
                "\r\n"
                + "This is a reminder email, Please complete your assigned test cases before Deadline."
                + "\r\n" +
                "If you have completed please update on the portal."
                + "\r\n" +
                "\r\n" +
                "Thanks, "
                + "\r\n"
                + "OMC Support Team"
                + "\r\n"
                + "dl.icg.global.cob.l3.support@imcnam.ssmb.com";

        try {
            emailService.emailSend(content, toEmailList, "Reminder for UAT pending test cases");
            List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());
            dataPopulate(data1, model);
        } catch (Exception ex) {
            System.out.println("Exception occurred while sending email");
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("exception", "Exception occurred while sending email!!  ");
            return "redirect:/releasemanager";
        }
        String reminderList = "";
        for (String email : toEmailList) {
            reminderList += email + " ";
        }
        reminderList += ".";
        System.out.println("Emails sent to people: " + reminderList);
        redirectAttributes.addFlashAttribute("success", "Reminder sent successfully to " + reminderList + "  ");


        return "redirect:/releasemanager";
    }

    @RequestMapping(value = "/releaseHandler", method = RequestMethod.POST, params = {"removeRelease"})
    public String deleteReleaseExcel(@ModelAttribute("excelRow") ExcelRow excelRow, Model model, Principal principal, RedirectAttributes redirectAttributes) {

        try {
            excelService.deleteExcel(excelRow.getReleaseName());
            redirectAttributes.addFlashAttribute("releaseDeleted","Release has been successfully deleted");

        } catch (Exception ex){
            redirectAttributes.addFlashAttribute("releaseNotDeleted","Release can't be deleted please refresh page and try again");
            return "releasemanager";
        }
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
        return "releasemanagerform";
    }

    public void dataPopulate(List<ExcelRow> data1, Model m) {

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
            m.addAttribute("deadlineDate",  data1.get(0).getDeadline());
            m.addAttribute("pass", pass);
            m.addAttribute("fail", fail);
            m.addAttribute("pending", pending);
            m.addAttribute("total", total);
        }
        m.addAttribute("data", data1);

    }

    @RequestMapping(value = "/saveRelease", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("excelRow") ExcelRow excelRow, Model m, Principal principal) {
        excelRow.setLastModUser(principal.getName());
        excelService.saveOrUpdate(excelRow);

        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());

        List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());
        dataPopulate(data1, m);
        return "releasemanager";
    }


    @RequestMapping(value = "/deleteExcelRow/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model m) {
        ExcelRow excelRow = excelService.getExcelRow(id);
        List<ExcelRow> data1 = excelService.getExcel(excelRow.getReleaseName());
        dataPopulate(data1, m);
        excelService.delete(id);
        return "releasemanager";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
    public String uploadFile(Model model, MultipartFile file, Principal principal, @RequestParam("releaseName") String releaseName, @RequestParam("deadline")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline, RedirectAttributes redirectAttributes) throws IOException {


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
                    redirectAttributes.addFlashAttribute("success", "Processing Excel File!!");
                    try {
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
                            excelRow.setStatus(UATstatus.PENDING.toString());
                            excelRow.setComments(entry.getValue().get(6).getContent());
                            excelRow.setLastModUser(principal.getName());
                            excelRow.setReleaseName(releaseName);
                            excelRow.setDeadline(Date.valueOf(deadline));

                            excel.add(excelRow);

                        }
                        model.addAttribute("deadlineDate", Date.valueOf(deadline));
                        excelService.saveorUpdateAll(excel);
                    } catch (IndexOutOfBoundsException a) {
                        System.out.println("User uploaded file in incorrect template");
                        redirectAttributes.addFlashAttribute("exception", "File Template does not look valid, Please upload file with correct Template.");
                        return "redirect:/newRelease";
                    }


                } else {
                    redirectAttributes.addFlashAttribute("exception", "This is not a valid excel file!");
                    return "redirect:/newRelease";
                }
            } else {
                redirectAttributes.addFlashAttribute("exception", "File missing! Please upload an excel file.");
                return "redirect:/newRelease";
            }

            redirectAttributes.addFlashAttribute("success", "File: " + file.getOriginalFilename()
                    + " has been uploaded and processed successfully!");
        } catch (FileNotFoundException f) {
            System.out.println("File not found Exception occured");
            redirectAttributes.addFlashAttribute("exception", "File missing! Please upload an excel file.");
            return "redirect:/newRelease";
        }
        return "redirect:/releasemanager";
    }

}

