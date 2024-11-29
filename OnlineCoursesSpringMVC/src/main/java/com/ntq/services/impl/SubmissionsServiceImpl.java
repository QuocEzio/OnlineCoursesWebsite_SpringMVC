/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Submissions;
import com.ntq.repository.SubmissionsRepository;
import com.ntq.services.SubmissionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Service
@Transactional
public class SubmissionsServiceImpl implements SubmissionsService{

    @Autowired
    private SubmissionsRepository submissionsRepository;

    @Override
    public Submissions getSubmissionByAssignmentAndUsername(Integer assignmentId, String username) {
        return submissionsRepository.findByAssignmentIdAndUsername(assignmentId, username);
    }

    @Override
    @Transactional
    public void saveOrUpdateSubmission(Submissions submission) {
        submissionsRepository.save(submission);
    }

    @Override
    public List<Submissions> getSubmissionsByAssignmentId(int assignmentId) {
        return this.submissionsRepository.getSubmissionsByAssignmentId(assignmentId);
    }

    @Override
    public void feedback(Submissions s) {
        this.submissionsRepository.feedback(s);
    }

    @Override
    public Submissions getSubmissionById(int id) {
        return this.submissionsRepository.getSubmissionById(id);
    }

    @Override
    public List<Submissions> getSubmissions() {
        return this.submissionsRepository.getSubmissions();
    }
    
    
}
