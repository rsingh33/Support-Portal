package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Backlog;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.BacklogService;
import com.citi.spring.web.service.HandoverService;
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
public class BacklogController {

    private static Logger logger = Logger.getLogger(BacklogController.class);
    @Autowired
    private UsersService usersService;
    @Autowired
    private BacklogService backlogService;
    @Autowired
    private HandoverService handoverService;


    @RequestMapping("/backlog")
    public String showBacklog(Model model, Principal principal) {
        try {
            logger.info("Getting backlog from DB for user: " + principal.getName());
            List<Backlog> backlog = backlogService.getCurrentBacklog();
            model.addAttribute("backlogs", backlog);
            logger.info("Backlog retrieved from DB for user: " + principal.getName());
        } catch (Exception e) {
            logger.error("Exception occurred while getting backlog from DB " + e.getCause(),e);
        }

        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        logger.info("Showing Backlog page for user: " + principal.getName());
        return "backlog";

    }

    @RequestMapping("/backlogForm")
    public String showBacklogNewIssueForm(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("backlog", new Backlog());
        logger.info("Showing Backlog form page for new Issue for: " + principal.getName());
        return "backlogForm";
    }


    @RequestMapping(value = "/backlogForm/{id}")
    public String editBacklogIssueForm(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());

        try {
            logger.info("Getting backlog issue from database for id  " + id + "to be edited by user:" + principal.getName());
            Backlog backlog = backlogService.getBacklog(id);
            m.addAttribute("backlog", backlog);
            logger.info("Showing backlog form for id: " + id + " for user: " + principal.getName());
        } catch (Exception e) {
            logger.error("Can't get edit form for " + id + "exception occurred "+ e.getCause(), e);
        }
        return "backlogForm";
    }

    @RequestMapping(value = "/saveBacklog", method = RequestMethod.POST)
    public String saveOrUpdateBacklog(@ModelAttribute("backlog") Backlog backlog, Principal principal, RedirectAttributes redirectAttributes) {
        backlog.setLastModUser(principal.getName());

        try {
            logger.info("Saving Backlog into database by user: " + principal.getName() + "value " + backlog.toString());
            backlogService.saveOrUpdate(backlog);
            logger.info("Backlog saved successfully" + "by user: " + principal + backlog.toString());
            redirectAttributes.addFlashAttribute("saved", "Record saved successfully");
        } catch (Exception ex) {
            logger.error("Backlog can not be saved " + ex.getCause() + " by user: " + principal.getName());
            logger.error(ex);
            redirectAttributes.addFlashAttribute("notSaved", "Record can't be saved, Please Try again ");
            return "redirect:/backlogForm";
        }
        return "redirect:/backlog";
    }

    @RequestMapping(value = "/moveToHandover/{id}", method = RequestMethod.GET)
    public String moveToHandover(@PathVariable int id, Principal principal, RedirectAttributes redirectAttributes) {


        try {
            logger.info("Getting Backlog for id: " + id + " by user: " + principal.getName());
            Backlog backlog = backlogService.getBacklog(id);
            logger.info("Converting Backlog to handover for id: " + id + " by user: " + principal.getName());
            Handover handover = backlogToHandover(backlog);
            backlogService.delete(id);

            logger.info("Moving backlog to handover for id: " + id);
            handoverService.saveOrUpdate(handover);
            logger.info("Backlog moved back to handover successfully for id: " + id);
            redirectAttributes.addFlashAttribute("moved", "Record moved to handover");
        } catch (Exception ex) {
            logger.error("Error while moving Backlog  to Handover for id: " + id + " by user:" + principal.getName() + ex.getCause());
            logger.error(ex);
            redirectAttributes.addFlashAttribute("notMoved", "Record can't be moved successfully");
            return "redirect:/backlog";
        }
        return "redirect:/backlog ";
    }

    private Handover backlogToHandover(Backlog backlog) {

        return new Handover(backlog.getLastMod(),
                backlog.getReportedBy(),
                backlog.getEmailSubject(),
                backlog.getTracking(),
                backlog.getComments(),
                backlog.getLastModUser(),
                backlog.getStatus(),
                backlog.getCurrentlyWith(),
                backlog.getEnvironment()
        );
    }

    @RequestMapping(value = "/deleteBacklog/{id}", method = RequestMethod.GET)
    public String deleteBacklog(@PathVariable int id, RedirectAttributes redirectAttributes, Principal principal) {
        try {
            logger.info("Deleting backlog for id: " + id + " by user: " + principal.getName());
            backlogService.delete(id);
            logger.info("Backlog successfully deleted for id: " + id + " by user: " + principal.getName());
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            logger.error("Backlog could not be deleted for id: " + id + " by user: " + principal.getName() + " "+ ex.getCause());
            logger.error(ex);
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            return "redirect:/backlog";
        }
        return "redirect:/backlog";
    }


}


