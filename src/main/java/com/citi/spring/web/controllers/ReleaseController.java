package com.citi.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ReleaseController {

    @RequestMapping(value = "/releasemanager")
    public String showReleaseManager(Model m, Principal principal) {
        if (principal != null)
            m.addAttribute("name", principal.getName());
        return "releasemanager";
    }
}
