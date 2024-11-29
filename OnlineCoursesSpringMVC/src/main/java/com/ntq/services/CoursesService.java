/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Courses;
import com.ntq.pojo.Lessons;
import java.util.List;
import java.util.Map;

/**
 *
 * @author QuocEzio
 */
public interface CoursesService {
    List<Courses> getCourses(Map<String, String> params);
    void addOrUpdate(Courses c);
    Courses getCourseById(int id);
    void deleteCourse(int id);
    void deleteCourse(Courses c);
    List<Lessons> getLessonsFormCourse(Courses c);
    long countCourses(Map<String, String> params);
}
