package com.citi.spring.web.controllers;

import com.citi.spring.web.validations.FormValidationGroup;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LoginController {

    private UsersService usersService;

    @Autowired
    public void setOffersService(UsersService offersService) {
        this.usersService = offersService;
    }


    @RequestMapping(value = "/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping(value = "/denied")
    public String showDenied() {

        return "denied";
    }


    @RequestMapping(value = "/loggedout")
    public String showLoggedOut() {

        return "redirect:/";
    }


    @RequestMapping(value = "/newaccount")
    public String showNewAccount(Model model) {
        model.addAttribute("user", new User());
        return "newaccount";
    }


    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newaccount";
        }


        user.setAuthority("ROLE_user");
        user.setEnabled(true);

        if (usersService.exists(user.getusername())) {

            result.rejectValue("username", "DuplicateKey.user.username");
            return ("newaccount");
        }
        try {
            usersService.create(user);
        } catch (DuplicateKeyException e) {


        }
        return "accountcreated";


    }

}
