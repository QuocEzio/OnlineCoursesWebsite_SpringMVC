/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.services.CartService;
import com.ntq.services.EnrollmentsService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author QuocEzio
 */
@Controller
public class EnrollmentsController {

    @Autowired
    private EnrollmentsService enrollmentsService;

    @Autowired
    private CartService cartService;

    @GetMapping("/addEnroll")
    public String addEnroll(Principal principal) {

        this.enrollmentsService.createEnrollFormCart(this.cartService.getCartByUsername(principal.getName()));
        this.cartService.removeAllCart(this.cartService.getCartByUsername(principal.getName()));

        return "redirect:/";
    }

}
