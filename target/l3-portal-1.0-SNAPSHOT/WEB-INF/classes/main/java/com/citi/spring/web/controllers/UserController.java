package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    private UsersService usersService;

    @Autowired
    public void setOffersService(UsersService offersService) {
        this.usersService = offersService;
    }


    @RequestMapping("/admin")
    public String showAdmin(Model model) {


        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

}
