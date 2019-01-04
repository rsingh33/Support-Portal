package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private UsersService usersService;

    @Autowired
    public void setOffersService(UsersService offersService) {
        this.usersService = offersService;
    }


    @RequestMapping("/admin")
    public String showAdmin(Model model, Principal principal) {
        List<User> users = usersService.getAllUsers();
        if (principal != null)
            model.addAttribute("name",usersService.findUserByUsername(principal.getName()).getName());
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        usersService.delete(username);
        return "redirect:/admin";
    }

}
