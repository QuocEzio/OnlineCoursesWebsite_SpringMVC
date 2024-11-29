/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.services.AssignmentsService;
import com.ntq.services.CartService;
import com.ntq.services.CoursesService;
import com.ntq.services.EnrollmentsService;
import com.ntq.services.LessonsService;
import com.ntq.services.OrdersService;
import com.ntq.services.SubmissionsService;
import com.ntq.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author QuocEzio
 */
@Controller
@ControllerAdvice
public class AdviceController {
    
    @Autowired
    private SubmissionsService submissionsService;
    @Autowired
   private OrdersService ordersService;
    
    @Autowired
    private AssignmentsService assignmentsService;
    
    @Autowired
    private CoursesService coursesService;
    
    @Autowired
    private EnrollmentsService enrollmentsService;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private LessonsService lessionsService;
    @ModelAttribute
    public void listSubmissions(Model model) {
         model.addAttribute("listSubmiss",this.submissionsService.getSubmissions());
    }
    @ModelAttribute
    public void listAssign(Model model) {
         model.addAttribute("listAssign",this.assignmentsService.getAssignments());
    }
    
    @ModelAttribute
    public void listOrders(Model model) {
         model.addAttribute("listOrders",this.ordersService.getListOrders());
    }
    
     @ModelAttribute
    public void listCourses(Model model) {
        model.addAttribute("listCourses", this.coursesService.getCourses(null));
    }
    
    @ModelAttribute
    public void listEnroll(Model model) {
         model.addAttribute("listEnroll",this.enrollmentsService.getListEnroll());
    }
    
    @ModelAttribute
    public void listUsers(Model model) {
        model.addAttribute("listUsers", this.usersService.getUsers());
    }
    
    @ModelAttribute
    public void listCarts(Model model) {
        model.addAttribute("listCarts", this.cartService.getListCart());
    }
    
     @ModelAttribute
    public void listLessons(Model model) {
        model.addAttribute("listLessons", this.lessionsService.getLessons());
    }
    
    
}
