/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Reviews;
import com.ntq.pojo.Users;
import com.ntq.repository.ReviewsRepository;
import java.util.Date;
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
public class ReviewsRepositoryImpl implements ReviewsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Reviews> getReviewByCourseId(int id, int page) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Reviews> query = b.createQuery(Reviews.class);
        Root root = query.from(Reviews.class);

        query = query.where(b.equal(root.get("courseId"), id));
        query = query.orderBy(b.desc(root.get("reviewId")));
        Query q = s.createQuery(query);

        int max = 3;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }

    @Override
    public long countReviews(int courseId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From Reviews Where courseId.courseId=:id");
        q.setParameter("id", courseId);
        return Long.parseLong(q.getSingleResult().toString());

    }

    @Override
    public void addReview(Reviews r, Users u) {
       Session s = this.sessionFactory.getObject().getCurrentSession();
        if(r.getReviewId()==null)
        {
            r.setUserId(u);
           r.setCreatedAt(new Date());
        }
        s.save(r);
    }


}
