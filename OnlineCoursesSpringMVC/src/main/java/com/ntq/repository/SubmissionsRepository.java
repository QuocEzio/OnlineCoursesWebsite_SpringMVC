/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.repository;


import com.ntq.pojo.Submissions;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface SubmissionsRepository {
    Submissions findByAssignmentIdAndUsername(Integer assignmentId, String username);
    void save(Submissions submission);
    List<Submissions> getSubmissionsByAssignmentId(int assignmentId);
    void feedback(Submissions s);
    Submissions getSubmissionById(int id);
    List<Submissions> getSubmissions();
}
