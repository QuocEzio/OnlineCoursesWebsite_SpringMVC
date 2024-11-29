/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.services.CartService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author QuocEzio
 */
@Controller
public class Cart {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public String addToCart(@RequestParam("courseId") int courseId,
            @RequestParam("username") String username
    ) {

        this.cartService.addToCart(courseId, username);

        return "redirect:/detailCart";
    }

    @GetMapping(path = "/detailCart")
    public String detailCart(Model model, Principal principal) {

        String username = principal.getName();
        model.addAttribute("carts", this.cartService.getCartByUsername(username));
        return "detailCartLayout";
    }

    @GetMapping(path = "/detailCart/deleteCartId/{cartId}")
    public String deleteCartId(@PathVariable(value = "cartId") int id) {

        this.cartService.removeFromCart(id);
        return "redirect:/detailCart";
    }
}
