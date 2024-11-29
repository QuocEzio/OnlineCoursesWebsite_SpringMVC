/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author QuocEzio
 */

@Controller
public class StatisticsController {
@Autowired
    private StatsService statsService;

    @GetMapping("/statistics")
    public String getRevenueStats(Model model) {
         List<Object[]> monthlyRevenue = statsService.getMonthlyRevenue();
    model.addAttribute("monthlyRevenue", monthlyRevenue);
    return "statisticsLayout";
    }
}
