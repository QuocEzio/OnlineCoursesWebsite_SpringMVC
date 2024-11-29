/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Users;
import com.ntq.services.EmailService;
import com.ntq.services.UsersService;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author QuocEzio
 */
@Controller
@RequestMapping("/change-password")
public class PasswordChangeController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailService emailService;

    private Map<String, String> verificationCodes = new HashMap<>();

    @GetMapping
    public String showChangePasswordForm() {
        return "changePassLayout";
    }

    @PostMapping("/send-code")
    public String sendVerificationCode(@RequestParam String email, Model model) {
        if (usersService.verifyEmail(email)) {
            String code = generateVerificationCode();
            verificationCodes.put(email, code);
            emailService.sendVerificationCode(email, code);
            model.addAttribute("email", email);
            model.addAttribute("codeSent", true);
            return "changePassLayout";
        } else {
            model.addAttribute("error", "The email does not exist in the system");
            return "changePassLayout";
        }
    }

    @PostMapping("/verify-code")
    public String verifyCodeAndChangePassword(@RequestParam String email, 
                                              @RequestParam String code, 
                                              @RequestParam String newPassword, 
                                              Model model) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            Users user = usersService.getUserByEmail(email);
            usersService.updatePassword(user.getUserId(), newPassword);
            verificationCodes.remove(email);
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", "Incorrect confirmation code");
        }
        return "changePassLayout";
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
