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
        logger.info("Showing home page....");
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        else
            return "redirect:/login";
        List<Monitor> urlEntities = monitorService.getMonitorEntities();
        model.addAttribute("urlEntities", urlEntities);
        return "home";

    }

    @RequestMapping(value = "/refresh")
    public String monitorApps(Model model, Principal principal) {
        logger.info("Showing Monitor page....");
       /* if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        else
            return "redirect:/login";*/
        // monitorService.saveOrUpdate();
        List<Monitor> urlEntities = monitorService.refresh();

        model.addAttribute("urlEntities", urlEntities);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveMonitor", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("monitor") Monitor monitor, Principal principal) {
        System.out.println("Entering save monitor");
        monitorService.saveOrUpdate(monitor);
        return "redirect:/";
    }


    @RequestMapping(value = "/monitorForm")
    public String showMonitor(Principal principal, Model model) {
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        model.addAttribute("env", Environment.values());
        model.addAttribute("monitor", new Monitor());
        return "monitorForm";
    }

    @RequestMapping(value = "/deleteMonitor/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        monitorService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/monitorForm/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {

        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        Monitor monitor = monitorService.getMonitor(id);
        m.addAttribute("env", Environment.values());
        m.addAttribute("monitor", monitor);
        return "monitorForm";
    }
}
