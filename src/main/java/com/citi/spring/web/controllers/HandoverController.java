package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Backlog;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.service.BacklogService;
import com.citi.spring.web.service.EmailService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HandoverController {

    private static Logger logger = Logger.getLogger(HandoverController.class);

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
        logger.info("Showing Handover page"  + " for user:" + principal);
        return "handover";

    }

    @RequestMapping("/handoverform")
    public String showform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("handover", new Handover());
        logger.info("Showing Handover form page" + " for user:" + principal);
        return "handoverform";
    }


    @RequestMapping(value = "/handoverform/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        Handover handover = handoverService.getHandover(id);
        m.addAttribute("handover", handover);
        logger.info("Retrieving Handover for id: " + id  + " by user:" + principal);
        return "handoverform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("handover") Handover handover, Principal principal, RedirectAttributes redirectAttributes) {
        handover.setLastModUser(principal.getName());
        try {
            handoverService.saveOrUpdate(handover);
            logger.info("Handover saved successfully" + " by user:" + principal + " " + handover.toString());
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            logger.error("Handover cannot be saved because " + ex.getCause()  + " by user:" + principal );
            logger.error(ex.getStackTrace());
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes,Principal principal) {
        try {
            handoverService.delete(id);
            logger.info("Handover deleted successfully for id: " + id + " by user:" + principal );
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            logger.error("Handover could not be deleted for this id: " + id + " by user:" + principal);
            logger.error(ex.getStackTrace());
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public String sendEmail(RedirectAttributes redirectAttributes, Principal principal) {
        List<Handover> handovers = handoverService.getCurrentHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        try {
            emailService.emailSend(content);
            logger.info("Handover sent successfully via mail" + " by user:" + principal);
            redirectAttributes.addFlashAttribute("emailSent", "Handover email sent successfully!!");
        } catch (Exception e) {
            logger.error("Handover email not sent" + " by user:" + principal);
            logger.error(e.getStackTrace());
            redirectAttributes.addFlashAttribute("exception", "Handover email could not be sent, please try again!!");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }


    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView getExcel(Principal principal) {
        List<Handover> handoverList = handoverService.getCurrentHandover();
        logger.info("Handover downloaded successfully" + " by user:" + principal);
        return new ModelAndView("handoverExcelView", "handoverList", handoverList);
    }

    @RequestMapping(value = "/moveToBacklog/{id}", method = RequestMethod.GET)
    public String moveToBacklog(@PathVariable int id, Principal principal, RedirectAttributes redirectAttributes) {
        Handover handover = handoverService.getHandover(id);
        Backlog backlog = handoverToBacklog(handover);
        try {
            handoverService.delete(id);
            logger.info("Handover deleted for id:" + id  + " by user:" + principal);
            backlogService.saveOrUpdate(backlog);
            logger.info("Handover moved in backlog successfully for id: " + id  + " by user:" + principal);
            redirectAttributes.addFlashAttribute("moved", "Record moved to backlog");
        } catch (Exception ex) {
            logger.error("Error while moving handover to backlog for id: " + id + " by user:" + principal);
            logger.error(ex.getStackTrace());
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


