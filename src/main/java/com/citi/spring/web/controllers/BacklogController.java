package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Backlog;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.BacklogService;
import com.citi.spring.web.service.HandoverService;
import com.citi.spring.web.service.UsersService;
import org.json.JSONArray;
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
public class BacklogController {

    @Autowired
    private UsersService usersService;
    private BacklogService backlogService;
    @Autowired
    private HandoverService handoverService;

    @Autowired
    public void setOffersService(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @RequestMapping("/backlog")
    public String showBacklog(Model model, Principal principal) {

        List<Backlog> handover = backlogService.getCurrentBacklog();
        model.addAttribute("backlogs", handover);
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        return "backlog";

    }

    @RequestMapping("/backlogForm")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("backlog", new Backlog());
        return "backlogForm";
    }


    @RequestMapping(value = "/backlogForm/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        Backlog backlog = backlogService.getBacklog(id);
        m.addAttribute("backlog", backlog);
        return "backlogForm";
    }

    @RequestMapping(value = "/saveBacklog", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("backlog") Backlog backlog, Principal principal) {
        backlog.setLastModUser(principal.getName());
        backlogService.saveOrUpdate(backlog);
        return "redirect:/backlog";
    }

    @RequestMapping(value = "/moveToHandover/{id}", method = RequestMethod.GET)
    public String moveToHandover(@PathVariable int id, Principal principal) {
        Backlog backlog = backlogService.getBacklog(id);
        Handover handover = backlogToHandover(backlog);
        backlogService.delete(id);
        handoverService.saveOrUpdate(handover);
        return "redirect:/handover ";
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
    public String delete(@PathVariable int id) {
        backlogService.delete(id);
        return "redirect:/backlog";
    }

  /*  @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public String sendEmail() {
        List<Backlog> backlogs = backlogService.getCurrentBacklog();
        String content = ListToHtmlTransformer.compose(backlogs);
        try {
            SendEmail.emailSend(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/backlog";
    }
*/

  /*  @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView getExcel() {
        List<Backlog> backlogList = backlogService.getCurrentBacklog();
        return new ModelAndView("backlogExcelView", "backlogList", backlogList);
    }*/
}


