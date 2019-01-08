package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.EmailService;
import com.citi.spring.web.service.UsersService;
import com.citi.spring.web.validations.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;


@Controller
public class PasswordController {

    @Autowired
    private UsersService userService;

    @Autowired
    private EmailService emailService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String displayForgotPasswordPage(Model model) {
        model.addAttribute("forgotlink", true);
        return "forgotPassword";
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String processForgotPasswordForm(ModelMap model, @ValidEmail @RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail

        model.addAttribute("forgotlink", false);
        if (!userService.existsByEmail(userEmail)) {
            model.addAttribute("message", "This is not a registered email " + userEmail);
            model.addAttribute("forgotlink", true);
        } else {

            // Generate random 36-character string token for reset password
            User user = userService.getUserEmail(userEmail);
            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.update(user);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath();

            // Email message
            String content = "Hi " + user.getName() +", \r\n"
                    +"To reset your password, click the link below:\n" + appUrl + "/reset?token=" + user.getResetToken()
                    +", \r\n" +
                    "\r\n" +
                    "Thanks, "
                    +"\r\n"
                    + "OMC Support Team"
                    +"\r\n"
                    + "dl.icg.global.cob.l3.support@imcnam.ssmb.com";
            System.out.println(content);
            emailService.emailSend(content, user.getEmail(), "Password Reset Request");

            // Add success message to view
            model.addAttribute("message", "A password reset link has been sent to " + userEmail);
            model.addAttribute("forgotlink", true);

        }


        return "forgotPassword";

    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET, params = "token")
    public String displayResetPasswordPage(Model model, @RequestParam(value = "token") String token) {

        User user = userService.findUserByResetToken(token);

        if (user != null) {

            model.addAttribute("resetToken", token);
        } else {

            model.addAttribute("message", "Oops!  Password Link expired. Please click the latest link or generate again using Forgot Password ");
        }
        model.addAttribute("isReset", true);
        model.addAttribute("resets", false);
        return "resetPassword";
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String setNewPassword(Model model, @RequestParam Map<String, String> requestParams) {

        // Find the user associated with the reset token
        if (requestParams.get("token").length() < 1) {
            model.addAttribute("message", "reset link expired please click" +
                    " on forgot password on home screen to regenerate");
            model.addAttribute("resets", false);
            model.addAttribute("isReset", true);
            return "resetPassword";
        }

        User user = userService.findUserByResetToken(requestParams.get("token"));


        // Set new password
        user.setPassword(passwordEncoder.encode(requestParams.get("password")));

        // Set the reset token to null so it cannot be used again
        user.setResetToken(null);

        // Save user
        userService.update(user);

        // In order to set a model attribute on a redirect, we must use
        // RedirectAttributes
        model.addAttribute("message", "You have successfully reset your password. " +
                " You may now ");
        model.addAttribute("resets", true);
        model.addAttribute("isReset", false);
        return "resetPassword";
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        System.out.println("Going to reset page without a token ...Exception occurred!!");
        return "redirect:/login";
    }
}