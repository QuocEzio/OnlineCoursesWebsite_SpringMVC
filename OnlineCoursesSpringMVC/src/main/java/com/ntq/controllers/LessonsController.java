/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Lessons;
import com.ntq.services.LessonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author QuocEzio
 */
@Controller
public class LessonsController {

    @Autowired
    private LessonsService lessionsService;

    @GetMapping(path = "/listLessons")
    public String listLessons(Model model) {
        model.addAttribute("lesson", this.lessionsService.getLessons());
        return "listLessonsLayout";
    }

    @GetMapping(path = "/uploadLesson")
    public String uploadCourse(Model model) {
        model.addAttribute("lesson", new Lessons());
        return "uploadLessonLayout";
    }

    @PostMapping(path = "/uploadLesson")
    public String uploadLessons(@ModelAttribute(value = "lesson") Lessons lesson
    ) {
        try {
            this.lessionsService.addOrUpdate(lesson);

            return "redirect:/listLessons";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return "uploadLessonLayout";
    }

    @GetMapping(path = "/updateLesson/{lessonId}")
    public String updateLesson(Model model, @PathVariable(value = "lessonId") int id) {

        model.addAttribute("lesson", this.lessionsService.getLessonByLessonId(id));
        return "uploadLessonLayout";
    }

    @GetMapping(path = "/deleteLesson/{lessonId}")
    public String deleteLesson(Model model, @PathVariable(value = "lessonId") int id) {

        this.lessionsService.deleteLesson(id);
        return "redirect:/listLessons";
    }

}
