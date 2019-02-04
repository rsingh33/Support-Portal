package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Issue;
import com.citi.spring.web.service.IssueService;
import com.citi.spring.web.service.UsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            List<Issue> issues = issueService.getCurrentIssues();
            logger.info("Issues fetch operation completed by user: " + principal.getName());
            model.addAttribute("issue", issues);
        }
        catch (Exception ex){
            logger.error("Can't retrieve issues from database " + ex.getCause(), ex);
        }
        return "issues";

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
        try {
            logger.info("Getting issue from database for id  " + id + "to be edited by user:" + principal.getName());
            Issue issue = issueService.getIssue(id);
            m.addAttribute("issue", issue);
            logger.info("Issue retrieved successfully for id: " + id);
        } catch(Exception ex){
            logger.error("Can't retrieve user for editing  ",ex);
        }

        return "issuesform";


    }

    @RequestMapping(value = "/saveIssue", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("issue") Issue issue, Principal principal, RedirectAttributes redirectAttributes) {
        issue.setUsername(principal.getName());

        try {
            logger.info("Saving issues into database by user: " + principal.getName() + "value " + issue.toString());
            issueService.saveOrUpdate(issue);
            logger.info("Issues saved successfully" + " by user:" + principal.getName() + " " + issue.toString());
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            logger.error("Issues cannot be saved because " + ex.getCause()  + " by user:" + principal.getName() );
            logger.error(ex);
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/issuesform";
        }

        return "redirect:/issues";
    }

    @RequestMapping(value = "/deleteIssue/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes, Principal principal) {

        try {
            logger.info("Issue delete started for id: " + id + " by user:" + principal.getName() );
            issueService.delete(id);
            logger.info("Issue deleted successfully for id: " + id + " by user:" + principal.getName() );
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            logger.error("Issue could not be deleted for this id: " + id + " by user:" + principal.getName() + " cause: " + ex.getCause());
            logger.error(ex);
            return "redirect:/issues";
        }
        return "redirect:/issues";
    }

}
