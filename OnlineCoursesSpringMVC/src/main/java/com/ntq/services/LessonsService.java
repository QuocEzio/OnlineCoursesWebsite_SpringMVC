/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Lessons;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface LessonsService {

    List<Lessons> getLessonsByCourseId(int id);

    List<Lessons> getLessons();

    void addOrUpdate(Lessons l);
    
    Lessons getLessonByLessonId(int id);
    
     void deleteLesson(int id);
}
