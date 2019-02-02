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
    @Autowired
    private HandoverService handoverService;
    @Autowired
    private BacklogService backlogService;
    @Autowired
    private EmailService emailService;


    @RequestMapping("/handover")
    public String showHandover(Model model, Principal principal) {
        try {
            List<Handover> handover = handoverService.getCurrentHandover();
            logger.info("Handover fetch operation completed by user: " + principal.getName());
            model.addAttribute("handovers", handover);
        } catch (Exception e) {
            logger.error("Exception occurred while getting handover from DB " + e.getCause(), e);
        }
        if (principal != null)
            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        logger.info("Showing Handover page " + " for user: " + principal.getName());
        return "handover";

    }

    @RequestMapping("/handoverform")
    public String showHandoverNewIssueform(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        m.addAttribute("handover", new Handover());
        logger.info("Showing Handover new issue form for user: " + principal.getName());
        return "handoverform";
    }


    @RequestMapping(value = "/handoverform/{id}")
    public String editHandoverIssueForm(@PathVariable int id, Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        logger.info("Handoverform operation started for app with id " + id + " by user: " + principal.getName());

        try {
            logger.info("Getting backlog issue from database for id  " + id + "to be edited by user:" + principal.getName());
            Handover handover = handoverService.getHandover(id);
            m.addAttribute("handover", handover);
            logger.info("Retrieving Handover for id: " + id + " by user: " + principal.getName());
        } catch (Exception e) {
            logger.error("Can't get edit form for " + id + "exception occurred " + e.getCause(), e);
        }
        return "handoverform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrUpdateHandover(@ModelAttribute("handover") Handover handover, Principal principal, RedirectAttributes redirectAttributes) {
        handover.setLastModUser(principal.getName());
        logger.info("Handoverform save operation started by user: " + principal.getName());
        try {
            logger.info("Saving handover into database by user: " + principal.getName() + "value " + handover.toString());
            handoverService.saveOrUpdate(handover);
            logger.info("Handover saved successfully" + " by user: " + principal.getName() + " value " + handover.toString());
            redirectAttributes.addFlashAttribute("saved", "Record successfully saved!!");
        } catch (Exception ex) {
            logger.error("Handover cannot be saved because " + ex.getCause() + " by user: " + principal.getName());
            logger.error(ex);
            redirectAttributes.addFlashAttribute("notSaved", "Could not be saved, Pleasse try again");
            return "redirect:/handoverform";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteHandover(@PathVariable int id, RedirectAttributes redirectAttributes, Principal principal) {
        logger.info("Handover delete operation started with id " + id + " by user: " + principal.getName());
        try {
            logger.info("Deleting handover for id: " + id + " by user: " + principal.getName());
            handoverService.delete(id);
            logger.info("Handover deleted successfully for id: " + id + " by user:" + principal.getName());
            redirectAttributes.addFlashAttribute("deleted", "Record deleted!!");
        } catch (Exception ex) {
            logger.error("Handover could not be deleted for this id: " + id + " by user:" + principal.getName() + " cause: " + ex.getCause());
            logger.error(ex);
            redirectAttributes.addFlashAttribute("deleteFailed", "Record could not be deleted");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public String sendEmailHandover(RedirectAttributes redirectAttributes, Principal principal) {
        logger.info("Getting current handover to be sent in handover email");
        List<Handover> handovers = handoverService.getCurrentHandover();

        String content = ListToHtmlTransformer.compose(handovers);
        try {
            logger.info("Sending email handover by user: " + principal.getName());
            emailService.emailSend(content);
            logger.info("Handover sent successfully via mail by user: " + principal.getName());
            redirectAttributes.addFlashAttribute("emailSent", "Handover email sent successfully!!");
        } catch (Exception e) {
            logger.error("Handover email not sent by user: " + principal.getName() + " cause: " + e.getCause());
            logger.error(e);
            redirectAttributes.addFlashAttribute("exception", "Handover email could not be sent, please try again!!");
            return "redirect:/handover";
        }

        return "redirect:/handover";
    }


    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView getExcel(Principal principal) {
        logger.info("Handover export to excel file triggered by user:" + principal.getName());

        List<Handover> handoverList = handoverService.getCurrentHandover();
             logger.info("Handover downloaded successfully by user: " + principal.getName());
        return new ModelAndView("handoverExcelView", "handoverList", handoverList);
    }

    @RequestMapping(value = "/moveToBacklog/{id}", method = RequestMethod.GET)
    public String moveToBacklog(@PathVariable int id, Principal principal, RedirectAttributes redirectAttributes) {

        try {
            logger.info("Getting handover for id: " + id + " by user: " + principal.getName());
            Handover handover = handoverService.getHandover(id);
            logger.info("Converting handover to backlog for id: " + id + " by user: " + principal.getName());
            Backlog backlog = handoverToBacklog(handover);

            handoverService.delete(id);

            backlogService.saveOrUpdate(backlog);
            logger.info("Handover moved to backlog successfully for id: " + id + " by user: " + principal.getName());
            redirectAttributes.addFlashAttribute("moved", "Record moved to backlog");
        } catch (Exception ex) {
            logger.error("Error while moving handover to backlog for id: " + id + " by user: " + principal.getName() + " cause: " + ex.getCause() );
            logger.error(ex);
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


