/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.services.OrdersService;
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

public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/paying")
    public String paying(@RequestParam("total") Long total,
            @RequestParam("username") String username
    ) {

        this.ordersService.addOrder(username, total);

        return "payingLayout";
    }

    @GetMapping("/orders")
    public String Orders(Model model) {

        model.addAttribute("orders", this.ordersService.getListOrders());

        return "ordersLayout";
    }

    @GetMapping(path = "/acceptOrder/{orderId}")
    public String acceptOrder(Model model, @PathVariable(value = "orderId") int id) {

        this.ordersService.acceptOrder(id);
        return "redirect:/orders";
    }
}
