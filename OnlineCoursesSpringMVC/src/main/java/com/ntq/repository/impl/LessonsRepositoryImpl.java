/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Lessons;

import com.ntq.repository.LessonsRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Repository
@Transactional
public class LessonsRepositoryImpl implements LessonsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional
    public List<Lessons> getLessonsByCourseId(int courseId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<Lessons> q = session.createQuery("FROM Lessons l WHERE l.courseId.courseId = :id", Lessons.class);
        q.setParameter("id", courseId);
        return q.getResultList();
    }

    @Override
    public List<Lessons> getLessons() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lessons> q = b.createQuery(Lessons.class);
        Root root = q.from(Lessons.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Lessons l) {
        Session s = this.factory.getObject().getCurrentSession();
        if (l.getLessonId() != null) {
            s.update(l);
        } else {
            s.save(l);
        }
    }

    @Override
    public Lessons getLessonByLessonId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Lessons.class, id);
    }

    @Override
    public void deleteLesson(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Lessons l = this.getLessonByLessonId(id);
        if (l != null) {
            s.delete(l);
        }
    }

   
}
