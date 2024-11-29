/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.formatters;

import com.ntq.pojo.Lessons;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author QuocEzio
 */
public class LessonsFormatter implements Formatter<Lessons>{
    @Override
    public String print(Lessons lesson, Locale locale) {
        return String.valueOf(lesson.getLessonId());
    }

    @Override
    public Lessons parse(String id, Locale locale) throws ParseException {
        Lessons l = new Lessons();
        l.setLessonId(Integer.parseInt(id));
        return l;
    }
}
