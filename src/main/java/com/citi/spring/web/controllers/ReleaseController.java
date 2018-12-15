package com.citi.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReleaseController {

    @RequestMapping(value = "/releasemanager")
    public String showReleaseManager(){
        return "releasemanager";
    }
}
