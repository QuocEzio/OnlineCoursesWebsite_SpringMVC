/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Courses;
import com.ntq.pojo.Lessons;
import com.ntq.pojo.Users;
import com.ntq.repository.CoursesRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class CoursesRepositoryImpl implements CoursesRepository {

    private static final int PAGE_SIZE = 2;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Courses> getCourses(Map<String, String> params) {

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Courses> q = b.createQuery(Courses.class);
        Root root = q.from(Courses.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("keyword");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("title"), String.format("%%%s%%", kw));
                predicates.add(p);
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                Predicate p = b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                predicates.add(p);
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                Predicate p = b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice));
                predicates.add(p);
            }

            String teacherName = params.get("teacherName");
            if (teacherName != null && !teacherName.isEmpty()) {
                Join<Courses, Users> userJoin = root.join("instructorId", JoinType.INNER);
                Predicate p = b.like(userJoin.get("fullName"), "%" + teacherName + "%");
                predicates.add(p);
            }

            q.where(predicates.toArray(new Predicate[0]));

            String sort = params.get("sort");
            if (sort != null && !sort.isEmpty()) {
                switch (sort) {
                    case "idAsc":
                        q.orderBy(b.asc(root.get("courseId")));
                        break;
                    case "idDesc":
                        q.orderBy(b.desc(root.get("courseId")));
                        break;
                    case "priceAsc":
                        q.orderBy(b.asc(root.get("price")));
                        break;
                    case "priceDesc":
                        q.orderBy(b.desc(root.get("price")));
                        break;
                }
            }
        }

        Query query = s.createQuery(q);

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                query.setFirstResult((page - 1) * PAGE_SIZE);
                query.setMaxResults(PAGE_SIZE);
            } else {
                query.setMaxResults(PAGE_SIZE);  
            }
        }

        return query.getResultList();

    }

    @Override
    public void addOrUpdate(Courses c) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (c.getCourseId() != null) {
            c.setUpdatedAt(new Date());
            s.update(c);
        } else {
            c.setCreatedAt(new Date());
            s.save(c);
        }
    }

    @Override
    public Courses getCourseById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        return s.get(Courses.class,
                 id);
    }

    @Override
    public void deleteCourse(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Courses c = this.getCourseById(id);
        if (c != null) {
            s.delete(c);
        }
    }

    @Override
    public void deleteCourse(Courses c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c != null && c.getCourseId() != null) {
            s.delete(c);
        }
    }

    @Override
    public List<Lessons> getLessonsFormCourse(Courses c) {
        Session s = this.factory.getObject().getCurrentSession();

        if (c != null && c.getLessonsSet() != null) {
            return new ArrayList<>(c.getLessonsSet());
        }
        return new ArrayList<>();

    }

    @Override
    public long countCourses(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(Courses.class);
        q.select(b.count(root));

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("keyword");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("title"), String.format("%%%s%%", kw));
                predicates.add(p);
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                Predicate p = b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                predicates.add(p);
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                Predicate p = b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice));
                predicates.add(p);
            }

            String teacherName = params.get("teacherName");
            if (teacherName != null && !teacherName.isEmpty()) {
                Join<Courses, Users> userJoin = root.join("instructorId", JoinType.INNER);
                Predicate p = b.like(userJoin.get("fullName"), "%" + teacherName + "%");
                predicates.add(p);
            }

            q.where(predicates.toArray(new Predicate[0]));
        }

        Query query = s.createQuery(q);
        return (Long) query.getSingleResult();
    }

}
