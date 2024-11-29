/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Assignments;
import com.ntq.repository.AssignmentsRepository;
import com.ntq.services.AssignmentsService;
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
public class AssignmentsServiceImpl implements AssignmentsService{
    
    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Override
    public List<Assignments> getAssignments() {
        return this.assignmentsRepository.getAssignments();
    }

    @Override
    public void addOrUpdate(Assignments a) {
        this.assignmentsRepository.addOrUpdate(a);
    }

    @Override
    public Assignments getAssignById(int id) {
        return this.assignmentsRepository.getAssignById(id);
    }

    @Override
    public void deleteAssign(int id) {
        this.assignmentsRepository.deleteAssign(id);
    }

    @Override
    public void addOrUpdateSubmiss(Assignments a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
