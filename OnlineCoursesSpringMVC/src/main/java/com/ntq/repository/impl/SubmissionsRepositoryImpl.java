/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;


import com.ntq.pojo.Submissions;
import com.ntq.repository.SubmissionsRepository;

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
@Transactional
@Controller
public class SubmissionsRepositoryImpl implements SubmissionsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

   @Override
    public Submissions findByAssignmentIdAndUsername(Integer assignmentId, String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Submissions WHERE assignmentId = :assignmentId AND username = :username");
        q.setParameter("assignmentId", assignmentId);
        q.setParameter("username", username);
        q.setMaxResults(1);
        return (Submissions) q.uniqueResult();
    }

    @Override
    public void save(Submissions submission) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(submission);
    }

    @Override
    public List<Submissions> getSubmissionsByAssignmentId(int assignmentId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Submissions WHERE assignmentId = :assignmentId");
        q.setParameter("assignmentId", assignmentId);
  
        return q.getResultList();
    }

    @Override
    
    public void feedback(Submissions sub) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query query = s.createQuery("UPDATE Submissions SET grade = :grade, feedback = :feedback WHERE submissionId = :id");
        query.setParameter("grade", sub.getGrade());
        query.setParameter("feedback", sub.getFeedback());
        query.setParameter("id", sub.getSubmissionId());
        query.executeUpdate();
    }

    @Override
    public Submissions getSubmissionById(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Submissions.class, id);
    }

    @Override
    public List<Submissions> getSubmissions() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Submissions> q = b.createQuery(Submissions.class);
        Root root = q.from(Submissions.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
