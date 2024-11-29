/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.formatters;


import com.ntq.pojo.Courses;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author QuocEzio
 */
public class CoursesFormatter implements Formatter<Courses> {

    @Override
    public String print(Courses course, Locale locale) {
        return String.valueOf(course.getCourseId());
    }

    @Override
    public Courses parse(String courseId, Locale locale) throws ParseException {
        Courses c = new Courses();
        c.setCourseId(Integer.parseInt(courseId));
        return c;
    }
    
}
