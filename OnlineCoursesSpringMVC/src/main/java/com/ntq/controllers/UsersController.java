/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Users;
import com.ntq.services.UsersService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author QuocEzio
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "loginLayout";
    }

    @GetMapping(path = "/register")
    public String registerView(Model model) {
        model.addAttribute("user", new Users());
        return "registerLayout";
    }

    @PostMapping(path = "/register")
    public String registerProcess(
            Model model,
            @ModelAttribute(name = "user") @Valid Users user,
            BindingResult result) {
        if (!result.hasErrors()) {
            String errMsg;
            if (user.getPassword().equals(user.getConfirmPassword())) {
                this.userDetailsService.addUser(user);
                return "redirect:/";
                
            } else {
                errMsg = "Password mismatch!";
            }
            model.addAttribute("errPassword", errMsg);
        }

        return "registerLayout";
    }

    @GetMapping(path = "/listUsers")
    public String listLessons(Model model) {
        model.addAttribute("user", this.userDetailsService.getUsers());
        return "listUsersLayout";
    }
    
    @GetMapping(path = "/updateUser/{userId}")
    public String updateUser(Model model, @PathVariable(value = "userId") int id) {

        model.addAttribute("user", this.userDetailsService.getUserById(id));
        return "uploadUserLayout";
    }

    @PostMapping(path = "/updateUser/{userId}")
    public String updateUserUser(Model model, @ModelAttribute(value = "user") @Valid Users user, BindingResult result
    ) {

        if (!result.hasErrors()) {
            this.userDetailsService.updateUser(user);
            return "redirect:/";
        }
        return "uploadUserLayout";
    }

    @GetMapping(path = "/deleteUser/{userId}")
    public String deleteUser(Model model, @PathVariable(value = "userId") int id) {

        this.userDetailsService.deleteUser(id);
        return "redirect:/listUsers";
    }

}
