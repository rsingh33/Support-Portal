package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.data.Enabled;
import com.citi.spring.web.dao.data.Roles;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.UsersService;
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

//    Changes for UserForm**********************

//    @RequestMapping("/userform")
//    public String showform(Model model, Principal principal) {
//        if (principal != null)
//            model.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
//        model.addAttribute("user",new User());
//        return "userform";
//    }


    @RequestMapping(value = "/userForm/{id}")
    public String edit(@PathVariable int id, Model m, Principal principal) {
        System.out.println("inUserEditMethod");
        if (principal != null)
            m.addAttribute("name", usersService.findUserByUsername(principal.getName()).getName());
        User user = usersService.getUser(id);
        m.addAttribute("user", user);
        m.addAttribute("authority", Roles.values());
        m.addAttribute("enabled", Enabled.values());
        return "userForm";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("user") User user, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "userForm";
        }

        System.out.println("Entering save user");
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
