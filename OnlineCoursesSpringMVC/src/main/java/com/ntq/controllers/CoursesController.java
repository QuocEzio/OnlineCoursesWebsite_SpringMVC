/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Courses;
import com.ntq.pojo.Reviews;
import com.ntq.services.CoursesService;
import com.ntq.services.ReviewsService;
import com.ntq.services.UsersService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author QuocEzio
 */
@Controller

public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private UsersService userDetailsService;

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping(path = "/listCourses")
    public String listCourses(Model model, @RequestParam Map<String, String> params) {

       int page = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        params.put("page", String.valueOf(page));  

        List<Courses> courses = this.coursesService.getCourses(params);
        long count = this.coursesService.countCourses(params);

        int totalPages = (int) Math.ceil((double) count / 2);  

        model.addAttribute("courses", courses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sort", params.get("sort"));
        
        return "listCoursesLayout";
    }

    @GetMapping(path = "/uploadCourse")
    public String uploadCourse(Model model) {
        model.addAttribute("course", new Courses());
        return "uploadCourseLayout";
    }

    @PostMapping(path = "/uploadCourse")
    public String uploadCourse(@ModelAttribute(value = "course") @Valid Courses course, BindingResult result
    ) {
        if (!result.hasErrors()) {
            this.coursesService.addOrUpdate(course);

            return "redirect:/listCourses";
        }

        return "uploadCourseLayout";
    }

    @GetMapping(path = "/updateCourse/{courseId}")
    public String updateCourse(Model model, @PathVariable(value = "courseId") int id) {

        model.addAttribute("course", this.coursesService.getCourseById(id));
        return "uploadCourseLayout";
    }

    @GetMapping(path = "/deleteCourse/{courseId}")
    public String deleteCourse(Model model, @PathVariable(value = "courseId") int id) {

        this.coursesService.deleteCourse(id);
        return "redirect:/listCourses";
    }

    @GetMapping(path = "/detailCourse/{courseId}")
    public String detailCourse(Model model,
            @PathVariable(value = "courseId") int id,
            @RequestParam(required = false) Map<String, String> params
    ) {

        model.addAttribute("course", this.coursesService.getCourseById(id));
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("reviews", this.reviewsService.getReviewByCourseId(id, page));
        model.addAttribute("reviewsCounter", this.reviewsService.countReviews(id));
        model.addAttribute("cmt", new Reviews());
        return "detailCourseLayout";
    }
    @PostMapping(path = "/detailCourse/{courseId}")
    public String reviewCourse(Model model, @PathVariable("courseId") int courseId,
            @ModelAttribute("cmt") Reviews review, Principal principal) {
        review.setCourseId(this.coursesService.getCourseById(courseId));
        this.reviewsService.addReview(review, userDetailsService.getUserByUsername(principal.getName()));
        return "redirect:/detailCourse/"+ courseId;
    }

}
