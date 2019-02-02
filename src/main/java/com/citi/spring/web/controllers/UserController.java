package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.data.Enabled;
import com.citi.spring.web.dao.data.Roles;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.UsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UsersService usersService;

    private static Logger logger = Logger.getLogger(UserController.class);


    @RequestMapping("/admin")
    public String showAdmin(Model model, Principal principal) {
        List<User> users = usersService.getAllUsers();
        logger.info("Retriving all usernames by user: " + principal.getName());
        if (principal != null)
            model.addAttribute("name",usersService.findUserByUsername(principal.getName()).getName());
        model.addAttribute("users", users);
        logger.info("Showing admin page for user: " + principal.getName());
        return "admin";
    }




    @RequestMapping(value = "/userForm/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        logger.info("Showing admin user form page for user: " + principal.getName());
        System.out.println("inUserEditMethod");
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        logger.info("Retriving usernames for id: " + id + " by user: " + principal.getName());
        User user = usersService.getUser(id);
        m.addAttribute("user", user);
        m.addAttribute("authority", Roles.values());
        m.addAttribute("enabled", Enabled.values());
        return "userForm";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("user") User user, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            logger.error("Error while saving for new user by user: " + principal.getName());
            return "userForm";
        }

        System.out.println("Entering save user");
        logger.info("Stroring user by user: " + principal.getName());
        usersService.saveOrUpdate(user);
        return "redirect:/admin";
    }



//    End******************





    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        usersService.delete(username);
        return "redirect:/admin";
    }

}
