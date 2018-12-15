package com.citi.spring.web.controllers;

import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.data.Status;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.HandoverService;
import com.citi.spring.web.validations.FormValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class HandoverController {

    private HandoverService handoverService;

    @Autowired
    public void setOffersService(HandoverService handoverService) {
        this.handoverService = handoverService;
    }

    @RequestMapping("/handover")
    public String showHandover(Model model) {

        List<Handover> handover = handoverService.getCurrentHandover();
        model.addAttribute("handovers", handover);
        return "handover";

    }

    @RequestMapping("/handoverform")
    public String showform(Model m){
        m.addAttribute("handover", new Handover());
        return "handoverform";
    }


    @RequestMapping(value="/handoverform/{id}")
    public String edit(@PathVariable int id, Model m){
        Handover handover=handoverService.getHandover(id);
        m.addAttribute("handover",handover);
        return "handoverform";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("handover") Handover handover){
        handoverService.saveOrUpdate(handover);
        return "redirect:/handover";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        handoverService.delete(id);
        return "redirect:/handover";
    }
}


