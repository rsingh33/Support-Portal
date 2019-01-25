package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Backlog;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.service.BacklogService;
import com.citi.spring.web.service.EmailService;
import com.citi.spring.web.service.HandoverService;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HandoverController {

    @Autowired
    private UsersService usersService;
    private HandoverService handoverService;
    @Autowired
    private BacklogService backlogService;
    @Autowired
    private EmailService emailService;

    @Autowired
    public void setOffersService(HandoverService handoverService) {
        this.handoverService = handoverService;
    }

    @RequestMapping("/handover")
    public String showHandover(Model model, Principal principal) {

        List<Handover> handover = handoverService.getCurrentHandover();
        model.addAttribute("handovers", handover);
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        return "handover";

    }

    @RequestMapping("/handoverform")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("handover", new Handover());
        return "handoverform";
    }


    @RequestMapping(value = "/handoverform/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        Handover handover = handoverService.getHandover(id);
        m.addAttribute("handover", handover);
        return "handoverform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("handover") Handover handover, Principal principal, RedirectAttributes redirectAttributes) {
        handover.setLastModUser(principal.getName());
        try {
            handoverService.saveOrUpdate(handover);
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            handoverService.delete(id);
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public String sendEmail(RedirectAttributes redirectAttributes) {
        List<Handover> handovers = handoverService.getCurrentHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        try {
            emailService.emailSend(content);
            redirectAttributes.addFlashAttribute("emailSent", "Handover email sent successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("exception", "Handover email could not be sent, please try again!!");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    public String getHistory(@PathVariable int id, Model model) {

        return "history";
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView getExcel() {
        List<Handover> handoverList = handoverService.getCurrentHandover();
        return new ModelAndView("handoverExcelView", "handoverList", handoverList);
    }

    @RequestMapping(value = "/moveToBacklog/{id}", method = RequestMethod.GET)
    public String moveToBacklog(@PathVariable int id, Principal principal, RedirectAttributes redirectAttributes) {
        Handover handover = handoverService.getHandover(id);
        Backlog backlog = handoverToBacklog(handover);
        try {
            handoverService.delete(id);
            backlogService.saveOrUpdate(backlog);
            redirectAttributes.addFlashAttribute("moved", "Record moved to backlog");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("notMoved", "Record can't be moved, Please try again");
            return "redirect:/handover";
        }
        return "redirect:/handover";
    }

    private Backlog handoverToBacklog(Handover handover) {
        return new Backlog(handover.getLastMod(),
                handover.getReportedBy(),
                handover.getEmailSubject(),
                handover.getTracking(),
                handover.getComments(),
                handover.getLastModUser(),
                handover.getStatus(),
                handover.getCurrentlyWith(),
                handover.getEnvironment());

    }
}


