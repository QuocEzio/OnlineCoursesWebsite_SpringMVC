/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author QuocEzio
 */
@Controller
public class HomeController {

    @RequestMapping(path = "/")
    
    public String Home()
    {
        return "homeLayout";
    }

}
