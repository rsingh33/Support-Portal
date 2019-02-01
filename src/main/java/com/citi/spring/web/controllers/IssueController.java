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
        model.addAttribute("issue", issues);
        logger.info("Showing Issues page by user: " + principal);
        return "issues";

    }

    @RequestMapping("/issuesform")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("issue", new Issue());
        logger.info("Showing Issues form page by user: " + principal);
        return "issuesform";
    }


    @RequestMapping(value = "/issuesform/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {

        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        Issue issue = issueService.getIssue(id);
        m.addAttribute("issue", issue);
        logger.info("Retrieving Issues for id: " + id  + " by user:" + principal);
        return "issuesform";
    }

    @RequestMapping(value = "/saveIssue", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("issue") Issue issue, Principal principal, RedirectAttributes redirectAttributes) {
        issue.setUsername(principal.getName());

        try {
            issueService.saveOrUpdate(issue);
            logger.info("Issues saved successfully" + " by user:" + principal + " " + issue.toString());
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            logger.error("Issues cannot be saved because " + ex.getCause()  + " by user:" + principal );
            logger.error(ex.getStackTrace());
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/issues";
        }

        return "redirect:/issues";
    }

    @RequestMapping(value = "/deleteIssue/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes, Principal principal) {
        try {
            issueService.delete(id);
            logger.info("Handover deleted successfully for id: " + id + " by user:" + principal );
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            logger.error("Handover could not be deleted for this id: " + id + " by user:" + principal);
            logger.error(ex.getStackTrace());
            return "redirect:/issues";
        }
        return "redirect:/issues";
    }

}
