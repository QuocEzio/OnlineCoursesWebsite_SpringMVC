/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Submissions;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface SubmissionsService {
    Submissions getSubmissionByAssignmentAndUsername(Integer assignmentId, String username);
    void saveOrUpdateSubmission(Submissions submission);
    List<Submissions> getSubmissionsByAssignmentId(int assignmentId);
    void feedback(Submissions s);
    Submissions getSubmissionById(int id);
     List<Submissions> getSubmissions();
    
}
