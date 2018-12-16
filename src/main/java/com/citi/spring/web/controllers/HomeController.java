package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.HandoverService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private HandoverService handoverService;



    @RequestMapping("/")
    public String showHome(Model model, Principal principal) {
        logger.info("Showing home page....");

        List<Handover> handover = handoverService.getCurrentHandover();
        model.addAttribute("handover", handover);

               return "home";

    }


}
