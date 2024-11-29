/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Submissions;
import com.ntq.services.SubmissionsService;
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
public class SubmissionsController {
    @Autowired
    private SubmissionsService submissionsService;
    
    @GetMapping("/listSubmissons/{assignmentId}")
    public String getSubmissionsByAssignment(@PathVariable Integer assignmentId, Model model) {
        
        model.addAttribute("submissions", submissionsService.getSubmissionsByAssignmentId(assignmentId));
        return "listSubmissionsLayout"; 
    }
    
    @GetMapping(path = "/feedback/{submissionId}")
    public String feedback(Model model, @PathVariable(value = "submissionId") int id) {
        Submissions s = this.submissionsService.getSubmissionById(id);

        model.addAttribute("submission",s );
        return "feedbackLayout";
    }
    
    @PostMapping(path = "/feedback/{submissionId}")
    public String feedback(@ModelAttribute(value = "submission")  Submissions submissions
    ) {
        
        this.submissionsService.feedback(submissions);
        return "redirect:/listAssignments";
    }
}
