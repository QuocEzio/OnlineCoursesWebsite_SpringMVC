/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntq.pojo.Courses;
import com.ntq.pojo.Lessons;
import com.ntq.repository.CoursesRepository;
import com.ntq.services.CoursesService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Service
@Transactional
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository ;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Courses> getCourses(Map<String, String> params) {
        return this.coursesRepository.getCourses(params);
    }

    @Override
    public void addOrUpdate(Courses c) {
        if (!c.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                c.setLogo(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CoursesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.coursesRepository.addOrUpdate(c);
    }

    @Override
    public Courses getCourseById(int id) {
        return this.coursesRepository.getCourseById(id);
    }

    @Override
    public void deleteCourse(int id) {
        this.coursesRepository.deleteCourse(id);
    }

    @Override
    public void deleteCourse(Courses c) {
        this.coursesRepository.deleteCourse(c);
    }

    @Override
    public List<Lessons> getLessonsFormCourse(Courses c) {
        return this.coursesRepository.getLessonsFormCourse(c);
    }
    @Override
    public long countCourses(Map<String, String> params) {
        return this.coursesRepository.countCourses(params);
    }
}
