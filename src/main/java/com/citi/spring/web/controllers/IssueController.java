package com.citi.spring.web.controllers;

import com.citi.spring.web.dao.entity.Issue;
import com.citi.spring.web.service.IssueService;
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
public class IssueController {


    @Autowired
    private IssueService issueService;



    @RequestMapping("/issues")
    public String showHome(Model model, Principal principal) {
        List<Issue> issues = issueService.getCurrentIssues();
        model.addAttribute("issue", issues);

        return "issues";

    }
    @RequestMapping("/issuesform")
    public String showform(Model m){
        m.addAttribute("issue", new Issue());
        return "issuesform";
    }


    @RequestMapping(value="/issuesform/{id}")
    public String edit(@PathVariable int id, Model m){
        Issue issue=issueService.getIssue(id);
        m.addAttribute("issue",issue);
        return "issuesform";
    }

    @RequestMapping(value="/saveIssue",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("issue") Issue issue){
        issueService.saveOrUpdate(issue);
        return "redirect:/issues";
    }

    @RequestMapping(value="/deleteIssue/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        issueService.delete(id);
        return "redirect:/issues";
    }

}
