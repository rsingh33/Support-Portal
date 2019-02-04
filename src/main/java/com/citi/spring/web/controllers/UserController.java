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
        logger.info("Showing user edit form page for user: " + principal.getName());

        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
     try {
         logger.info("Retriving user for id: " + id + " by user: " + principal.getName());

         User user = usersService.getUser(id);
         m.addAttribute("user", user);
         m.addAttribute("authority", Roles.values());
         m.addAttribute("enabled", Enabled.values());
     } catch (Exception ex){
         logger.error("User can't be retrieved for editing",ex);
     }
        return "userForm";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("user") User user, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            logger.error("Error while saving for new user by user: " + principal.getName() + user.toString());
            return "userForm";
        }



     try {
         logger.info("Storing user  "+ user.toString() + " by user " + principal.getName());
         usersService.saveOrUpdate(user);
         logger.info("User Saved "+ user.toString() + " by user " + principal.getName());
     } catch (Exception ex){
         logger.error("user can't be saved  "+ user.toString() + " by user " + principal.getName(), ex);
     }
        return "redirect:/admin";
    }




    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
       try {
           usersService.delete(username);
           logger.info("User deleted for username: " + username);
       } catch(Exception ex){
           logger.error("User can't be deleted ", ex);
       }
        return "redirect:/admin";
    }

}
