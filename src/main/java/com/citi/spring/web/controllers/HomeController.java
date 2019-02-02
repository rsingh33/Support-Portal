package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.entity.Monitor;
import com.citi.spring.web.service.HandoverService;
import com.citi.spring.web.service.MonitorService;
import com.citi.spring.web.service.UsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);
    @Autowired
    MonitorService monitorService;
    @Autowired
    private HandoverService handoverService;
    @Autowired
    private UsersService usersService;

    @RequestMapping("/")
    public String showHome(Model model, Principal principal) {

        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        else {
            logger.info("Redirected to Login page as user is not logged in");
            return "redirect:/login";
        }

        try {
            List<Monitor> urlEntities = monitorService.getMonitorEntities();
            logger.info("Got urls from database to display on home page ");
            model.addAttribute("urlEntities", urlEntities);
        } catch (Exception ex) {
            logger.error("Exception occurred while getting urls from database" + ex.getStackTrace());
        }

        return "home";

    }

    @RequestMapping(value = "/refresh")
    public String monitorApps(Model model, Principal principal, RedirectAttributes redirectAttributes) {

        logger.info("Refresh operation started for all URLS by user: " + principal.getName());
        try {
            List<Monitor> urlEntities = monitorService.refresh();
            model.addAttribute("urlEntities", urlEntities);
            logger.info("Refresh operation completed for all URLS by user: " + principal.getName());
        } catch (Exception ex) {
            logger.error("Execption occurred while refreshing urls " + ex.getStackTrace());
        }

        redirectAttributes.addFlashAttribute("refreshed", "All status and response times refreshed sucessfully");
        return "redirect:/";
    }

    @RequestMapping(value = "/refresh/{id}")
    public String refreshOne(@PathVariable int id, Model m, RedirectAttributes redirectAttributes, Principal principal) {
        logger.info("Refresh operation started for app with id " + id + " by user: " + principal.getName());
        try {
            monitorService.refreshOne(id);
            logger.info("Refresh operation completed for app with id " + id + " by user: " + principal.getName());
        } catch (Exception ex) {
            logger.error("Execption occurred while refreshing url with id " + id + " " + ex.getStackTrace());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/saveMonitor", method = RequestMethod.POST)
    public String saveOrUpdateMonitorApp(@ModelAttribute("monitor") Monitor monitor, Principal principal) {
        try {
            logger.info("About to save monitor app " + monitor.toString());
            monitorService.saveOrUpdate(monitor);
            logger.info("Monitoring attribute saved and updated successfully by user: " + principal.getName());
        } catch (Exception ex) {
            logger.error("Error occurred while saving monitoring app " + monitor.toString());
        }

        return "redirect:/";
    }


    @RequestMapping(value = "/monitorForm")
    public String showMonitor(Principal principal, Model model) {
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        model.addAttribute("env", Environment.values());
        model.addAttribute("monitor", new Monitor());
        logger.info("Showing new app monitor form page for user: " + principal.getName());
        return "monitorForm";
    }

    @RequestMapping(value = "/deleteMonitor/{id}", method = RequestMethod.GET)
    public String deleteMonitorApp(@PathVariable int id, Principal principal) {
        try {
            monitorService.delete(id);
            logger.info("Monitoring attribute deleted successfully for id: " + id + " by user: " + principal.getName());
        } catch (Exception ex) {
            logger.error("Monitoring app can't be deleted " + ex.getStackTrace());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/monitorForm/{id}")
    public String editMonitorApp(@PathVariable int id, Model m, Principal principal) {

        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        try {
            Monitor monitor = monitorService.getMonitor(id);
            m.addAttribute("env", Environment.values());
            m.addAttribute("monitor", monitor);
            logger.info("Monitoring attribute retrieved from db successfully for id: " + id + " by user: " + principal.getName());
        } catch (Exception ex) {
            logger.error("Monitoring app can't be retrieved for id " + id + " " + ex.getStackTrace());
        }
        return "monitorForm";
    }
}
