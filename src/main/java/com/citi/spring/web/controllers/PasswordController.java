package com.citi.spring.web.controllers;


import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.service.EmailService;
import com.citi.spring.web.service.UsersService;
import com.citi.spring.web.validations.ValidEmail;
import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;


@Controller
public class PasswordController {

    private static Logger logger = Logger.getLogger(PasswordController.class);
    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String displayForgotPasswordPage(Model model) {
        model.addAttribute("forgotlink", true);
        logger.info("Showing forget password page");
        return "forgotPassword";
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String processForgotPasswordForm(ModelMap model, @ValidEmail @RequestParam("email") String userEmail, HttpServletRequest request, RedirectAttributes redirectAttributes) {


        logger.info("Checking if a user exists by email " + userEmail + " in the database");

        model.addAttribute("forgotlink", false);
        if (!usersService.existsByEmail(userEmail)) {
            model.addAttribute("message", "This is not a registered email " + userEmail);
            model.addAttribute("forgotlink", true);
            logger.warn(userEmail + " does not exist");
        } else {
            logger.info("User found with email " + userEmail + " in the database");
            try {
                // Generate random 36-character string token for reset password
                User user = usersService.getUserEmail(userEmail);
                user.setResetToken(UUID.randomUUID().toString());
                logger.info("Token generated for user with email Id: " + userEmail);

                // Save token to database
                usersService.update(user);
                logger.info("Token Saved in database");

                String appUrl = request.getScheme() + "://" + request.getServerName() + ":" +
                        request.getServerPort() + request.getContextPath();

                // Email message
                String content = "Hi " + user.getName() + ", \r\n"
                        + "To reset your password, click the link below:\n" + appUrl + "/reset?token=" + user.getResetToken()
                        + ", \r\n" +
                        "\r\n" +
                        "Thanks, "
                        + "\r\n"
                        + "OMC Support Team"
                        + "\r\n"
                        + "dl.icg.global.cob.l3.support@imcnam.ssmb.com";


                logger.info("Forget password Email sent to: " + userEmail);
                emailService.emailSend(content, user.getEmail(), "Password Reset Request");
                redirectAttributes.addAttribute("message", "A password reset link has been sent to " + userEmail);
                model.addAttribute("forgotlink", true);

            } catch (Exception ex) {
                logger.error("Forget password Email unsuccessful for email id: " + userEmail + " cause: " + ex.getCause());
                logger.error(ex);
                redirectAttributes.addFlashAttribute("exception", "Forget password Email unsuccessful for email id: \" + userEmail + \" cause: \" + ex.getCause()");
            }

            // Add success message to view


        }


        return "forgotPassword";

    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET, params = "token")
    public String displayResetPasswordPage(Model model, @RequestParam(value = "token") String token) {

        User user = usersService.findUserByResetToken(token);
        try {
            if (user != null) {

                model.addAttribute("resetToken", token);
                logger.info("Token Active");
            } else {

                model.addAttribute("message", "Oops!  Password Link expired. Please click the latest link or generate again using Forgot Password ");
                logger.warn("Token Expired");
            }
            model.addAttribute("isReset", true);
            model.addAttribute("resets", false);
        } catch (Exception ex) {
            logger.error("Issue found with password reset link and displaying password reset page" + ex.getCause(), ex);
        }
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
            logger.warn("Password Link expired");
            return "resetPassword";
        }

        User user = usersService.findUserByResetToken(requestParams.get("token"));
        logger.info("User: " + user.getName() + " found for the Token");


        // Set new password
        user.setPassword(passwordEncoder.encode(requestParams.get("password")));
        logger.info("New Password set successfully for user: " + user);

        // Set the reset token to null so it cannot be used again
        user.setResetToken(null);
        logger.info("Token reset to null");

        // Save user
        usersService.update(user);
        logger.info("Password updated for user: " + user);

        // In order to set a model attribute on a redirect, we must use
        // RedirectAttributes
        model.addAttribute("message", "You have successfully reset your password. " +
                " You may now ");
        logger.info("Password reset successful");
        model.addAttribute("resets", true);
        model.addAttribute("isReset", false);
        return "resetPassword";
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        System.out.println("Going to reset page without a token ...Exception occurred!!");
        logger.warn("Exception occurred in Reset Password page");
        return "redirect:/login";
    }
}