package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Issue;
import com.citi.spring.web.service.IssueService;
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
public class IssueController {


    private static Logger logger = Logger.getLogger(IssueController.class);
    @Autowired
    private IssueService issueService;
    @Autowired
    private UsersService usersService;


    @RequestMapping("/issues")
    public String showHome(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        List<Issue> issues = issueService.getCurrentIssues();
        logger.info("Issues fetch operation completed by user: " + principal.getName());
        model.addAttribute("issue", issues);
        return "issues";

//        try {
//            List<Handover> handover = handoverService.getCurrentHandover();
//            logger.info("Handover fetch operation completed by user: " + principal.getName());
//            model.addAttribute("handovers", handover);
//        } catch (Exception e) {
//            logger.error("Exception occurred while getting handover from DB " + e.getCause(), e);
//        }
//        if (principal != null)
//            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
//        logger.info("Showing Handover page " + " for user: " + principal.getName());
//        return "handover";

    }

    @RequestMapping("/issuesform")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("issue", new Issue());
        logger.info("Showing Issues form page by user: " + principal.getName());
        return "issuesform";
    }


    @RequestMapping(value = "/issuesform/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        logger.info("Issues form operation started for app with id " + id + " by user: " + principal.getName());
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        logger.info("Getting issue from database for id  " + id + "to be edited by user:" + principal.getName());
        Issue issue = issueService.getIssue(id);
        m.addAttribute("issue", issue);

        return "issuesform";

//        if (principal != null)
//            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
//        logger.info("Handoverform operation started for app with id " + id + " by user: " + principal.getName());
//
//        try {
//            logger.info("Getting backlog issue from database for id  " + id + "to be edited by user:" + principal.getName());
//            Handover handover = handoverService.getHandover(id);
//            m.addAttribute("handover", handover);
//            logger.info("Retrieving Handover for id: " + id + " by user: " + principal.getName());
//        } catch (Exception e) {
//            logger.error("Can't get edit form for " + id + "exception occurred " + e.getCause(), e);
//        }
//        return "handoverform";
    }

    @RequestMapping(value = "/saveIssue", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("issue") Issue issue, Principal principal, RedirectAttributes redirectAttributes) {
        issue.setUsername(principal.getName());
        logger.info("Issues save operation started by user: " + principal.getName());

        try {
            logger.info("Saving issues into database by user: " + principal.getName() + "value " + issue.toString());
            issueService.saveOrUpdate(issue);
            logger.info("Issues saved successfully" + " by user:" + principal.getName() + " " + issue.toString());
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            logger.error("Issues cannot be saved because " + ex.getCause()  + " by user:" + principal );
            logger.error(ex);
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/issues";
        }

        return "redirect:/issues";
    }

    @RequestMapping(value = "/deleteIssue/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes, Principal principal) {
        logger.info("Issues delete operation started for app with id " + id + " by user: " + principal.getName());
        try {
            logger.info("Handover deleted started for id: " + id + " by user:" + principal.getName() );
            issueService.delete(id);
            logger.info("Handover deleted successfully for id: " + id + " by user:" + principal.getName() );
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            logger.error("Handover could not be deleted for this id: " + id + " by user:" + principal.getName() + " cause: " + ex.getCause());
            logger.error(ex);
            return "redirect:/issues";
        }
        return "redirect:/issues";
    }

}
