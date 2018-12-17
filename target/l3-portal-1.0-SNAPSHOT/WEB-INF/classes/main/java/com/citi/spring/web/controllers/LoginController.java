package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.emailHandler.SendEmail;
import com.citi.spring.web.service.UsersService;
import com.citi.spring.web.validations.FormValidationGroup;
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
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String displayForgotPasswordPage() {
        return "forgotPassword";
    }


    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) throws Exception {
        User user = usersService.findUserByEmail(userEmail);
        if (user != null) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            usersService.update(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();

            System.out.println(appUrl);

            SendEmail.emailSend("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.getResetToken(), user.getEmail(), "Password Reset Request");
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }
    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        if (usersService.exists(token)) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
       User user = usersService.findUserByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (usersService.exists(requestParams.get("token"))) {

            User resetUser = user;

            // Set new password
            resetUser.setPassword(passwordEncoder.encode(requestParams.get("password")));
            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);

            // Save user
            usersService.update(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:home");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:home");
    }
}
