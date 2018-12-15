package com.citi.spring.web.controllers;

import com.citi.spring.web.validations.FormValidationGroup;
import com.citi.spring.web.dao.entity.Offer;
import com.citi.spring.web.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OffersController {

    private OffersService offersService;

    @Autowired
    public void setOffersService(OffersService offersService) {
        this.offersService = offersService;
    }

    @RequestMapping("/offers")
    public String showOffers(Model model) {

        List<Offer> offers = offersService.getCurrent();
        model.addAttribute("offers", offers);
        return "offers";

    }

    @RequestMapping("/createoffer")
    public String createOffer(Model model, Principal principal) {

        Offer offer = null;
        if ((principal != null)) {
            String username = principal.getName();
            offer = offersService.getOffer(username);
        }

        if (offer == null) {
            offer = new Offer();
        }
        model.addAttribute("offer", offer);
        return "createoffer";

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest(Model model, @RequestParam("id") String id) {

        System.out.println("id is: " + id);
        return "home";

    }

    @RequestMapping(value = "/doCreate", method = RequestMethod.POST)
    public String doCreate(@Validated(FormValidationGroup.class) Offer offer, BindingResult result, Model model, Principal principal, @RequestParam(value = "delete", required = false) String delete) {

        if (result.hasErrors()) {
            return "createoffer";
        }
        String username = principal.getName();
        if (delete == null) {

            offer.getUser().setusername(username);
            offersService.saveOrUpdate(offer);
            return "offercreated";
        } else {

            offersService.delete(offer.getId());
            return "offerdeleted";
        }

    }

}