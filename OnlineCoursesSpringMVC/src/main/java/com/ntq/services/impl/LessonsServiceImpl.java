/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Lessons;

import com.ntq.repository.LessonsRepository;

import com.ntq.services.LessonsService;
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
public class LessonsServiceImpl implements LessonsService {

    @Autowired
    private LessonsRepository lessonsRepository;

    @Transactional
    @Override
    public List<Lessons> getLessonsByCourseId(int id) {
        return this.lessonsRepository.getLessonsByCourseId(id);
    }

    @Override
    public List<Lessons> getLessons() {
       return this.lessonsRepository.getLessons();
    }

    @Override
    public void addOrUpdate(Lessons l) {
        this.lessonsRepository.addOrUpdate(l);
    }

    @Override
    public Lessons getLessonByLessonId(int id) {
       return this.lessonsRepository.getLessonByLessonId(id);
    }

    @Override
    public void deleteLesson(int id) {
       this.lessonsRepository.deleteLesson(id);
    }

   

    

}
