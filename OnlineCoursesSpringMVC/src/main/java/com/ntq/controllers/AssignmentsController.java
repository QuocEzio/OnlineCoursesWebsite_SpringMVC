/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Assignments;
import com.ntq.pojo.Submissions;
import com.ntq.services.AssignmentsService;
import com.ntq.services.SubmissionsService;
import java.security.Principal;
import java.util.Date;
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
public class AssignmentsController {

    @Autowired
    private AssignmentsService assignmentsService;

    @Autowired
    private SubmissionsService submissionsService;

    @GetMapping("/listAssignments")
    public String listAssignments(Model model) {
        model.addAttribute("assign", this.assignmentsService.getAssignments());
        return "listAssignmentsLayout";
    }

    @GetMapping(path = "/uploadAssignment")
    public String uploadAssignment(Model model) {
        model.addAttribute("assign", new Assignments());
        return "uploadAssignmentLayout";
    }

    @PostMapping(path = "/uploadAssignment")
    public String uploadAssignment(@ModelAttribute(value = "assign") Assignments assignment
    ) {
        try {
            this.assignmentsService.addOrUpdate(assignment);

            return "redirect:/listAssignments";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return "uploadAssignmentLayout";
    }

    @GetMapping(path = "/updateAssignment/{assignmentId}")
    public String updateAssignment(Model model, @PathVariable(value = "assignmentId") int id) {

        model.addAttribute("assign", this.assignmentsService.getAssignById(id));
        return "uploadAssignmentLayout";
    }

    @GetMapping(path = "/deleteAssignment/{assignmentId}")
    public String deleteAssignment(Model model, @PathVariable(value = "assignmentId") int id) {

        this.assignmentsService.deleteAssign(id);
        return "redirect:/listAssignments";
    }

    @GetMapping("/submitAssignment/{assignmentId}")
    public String showSubmissionForm(@PathVariable int assignmentId, Model model, Principal principal) {
        String username = principal.getName();
        Assignments assignment = assignmentsService.getAssignById(assignmentId);
        Submissions existingSubmission = submissionsService.getSubmissionByAssignmentAndUsername(assignmentId, username);

        if (existingSubmission == null) {
            existingSubmission = new Submissions();
            existingSubmission.setAssignmentId(assignmentId);
            existingSubmission.setUsername(username);
        }

        model.addAttribute("submission", existingSubmission);
        model.addAttribute("assignment", assignment);
        return "submitAssignmentLayout";
    }

    @PostMapping("/submitAssignment")
    public String submitAssignment(@ModelAttribute Submissions submission, Principal principal) {
        submission.setUsername(principal.getName());
        submission.setSubmittedAt(new Date());
        submissionsService.saveOrUpdateSubmission(submission);
        return "redirect:/listAssignments";
    }

}
