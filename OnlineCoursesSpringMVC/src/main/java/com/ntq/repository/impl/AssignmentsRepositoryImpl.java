/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Assignments;
import com.ntq.repository.AssignmentsRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Controller
@Transactional
public class AssignmentsRepositoryImpl implements AssignmentsRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Assignments> getAssignments() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Assignments> q = b.createQuery(Assignments.class);
        Root root = q.from(Assignments.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Assignments a) {
        Session s = this.factory.getObject().getCurrentSession();
        if (a.getAssignmentId() != null) {
            s.update(a);
        } else {
            s.save(a);
        }
    }

    @Override
    public Assignments getAssignById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Assignments.class, id);
    }

    @Override
    public void deleteAssign(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Assignments a = this.getAssignById(id);
        if (a != null) {
            s.delete(a);
        }
    }

    
    
}
