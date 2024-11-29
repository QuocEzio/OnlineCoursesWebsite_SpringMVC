/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Assignments;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface AssignmentsService {
     List<Assignments> getAssignments();
     void addOrUpdate(Assignments a);
     Assignments getAssignById(int id);
     void deleteAssign(int id);
     void addOrUpdateSubmiss(Assignments a);
}
