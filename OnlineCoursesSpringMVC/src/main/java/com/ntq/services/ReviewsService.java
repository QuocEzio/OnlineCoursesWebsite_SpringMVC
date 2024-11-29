/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Reviews;
import com.ntq.pojo.Users;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface ReviewsService {
    List<Reviews> getReviewByCourseId(int id,int page);
    long countReviews(int courseId);
    void addReview(Reviews r, Users u);
}
