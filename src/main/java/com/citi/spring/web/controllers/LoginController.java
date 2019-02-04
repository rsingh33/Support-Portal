package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.data.Roles;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.emailHandler.SendEmail;
import com.citi.spring.web.service.UsersService;
import com.citi.spring.web.validations.FormValidationGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private UsersService usersService;





    @RequestMapping(value = "/login")
    public String showLogin() {
        logger.info("Showing Login Page");
        return "login";
    }

    @RequestMapping(value = "/denied")
    public String showDenied(Principal principal) {

        logger.info("Excess Denied for user " );
        return "denied";
    }


    @RequestMapping(value = "/loggedout")
    public String showLoggedOut(Principal principal) {


        return "redirect:/login";
    }


    @RequestMapping(value = "/newaccount")
    public String showNewAccount(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("newAcc", true);
        model.addAttribute("accCreated", false);
        logger.info("Displaying new account form");
        return "newaccount";
    }


    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("newAcc", true);
            model.addAttribute("accCreated", false);
            logger.warn(" User got from UI has errors so resetting the form");
            return "newaccount";
        }


        user.setAuthority("ROLE_user");
        user.setEnabled(true);

        if (usersService.exists(user.getusername())) {

            result.rejectValue("username", "DuplicateKey.user.username");
            logger.warn("Username " + user.getusername() + " already exists" );
            return ("newaccount");
        }
        try {

            logger.info("Creating account for user " + user.toString());
            usersService.create(user);
            logger.info("account created successfully for " + user.getName() + " with email: " + user.getEmail() );
        } catch (DuplicateKeyException e) {
            model.addAttribute("newAcc", true);
            model.addAttribute("accCreated", false);
            logger.error("Account creation failed " + e.getCause() , e);
            return "newaccount";
        }
        model.addAttribute("accCreated", true);
        model.addAttribute("newAcc", false);
        return "newaccount";

    }


}
