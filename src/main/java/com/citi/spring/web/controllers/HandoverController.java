package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.emailHandler.SendEmail;
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

import java.security.Principal;
import java.util.List;

@Controller
public class HandoverController {

    @Autowired
    private UsersService usersService;
    private HandoverService handoverService;

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
    public String saveOrUpdate(@ModelAttribute("handover") Handover handover, Principal principal) {
        handover.setUsername(principal.getName());
        handoverService.saveOrUpdate(handover);
        return "redirect:/handover";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        handoverService.delete(id);
        return "redirect:/handover";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public String sendEmail() {
        List<Handover> handovers = handoverService.getCurrentHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        try {
            SendEmail.emailSend(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/handover";
    }


    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView getExcel() {
        List<Handover> handoverList = handoverService.getCurrentHandover();
        return new ModelAndView("handoverExcelView", "handoverList", handoverList);
    }
}


