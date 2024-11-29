/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Cart;
import com.ntq.pojo.Enrollments;
import com.ntq.repository.EnrollmentsRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.stereotype.Controller;

/**
 *
 * @author QuocEzio
 */
@Controller
public class EnrollmentsRepositoryImpl implements EnrollmentsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void createEnrollFormCart(List<Cart> carts) {

        Session s = this.factory.getObject().getCurrentSession();
        List<Enrollments> enroll = new ArrayList<>();
        for (Cart cart : carts) {
            Enrollments enrollment = new Enrollments();
            enrollment.setCourseId(cart.getCourseId());
            enrollment.setUsername(cart.getUsername());
            enrollment.setEnrollmentDate(new Date());

            enroll.add(enrollment);
        }

        for (Enrollments enrollment : enroll) {
            s.save(enrollment);
        }

    }

    @Override
    public List<Enrollments> getListEnroll() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Enrollments> q = b.createQuery(Enrollments.class);
        Root root = q.from(Enrollments.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

}
