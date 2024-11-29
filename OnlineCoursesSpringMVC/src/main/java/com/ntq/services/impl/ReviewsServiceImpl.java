/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Reviews;
import com.ntq.pojo.Users;
import com.ntq.repository.ReviewsRepository;
import com.ntq.services.ReviewsService;
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
public class ReviewsServiceImpl implements ReviewsService{
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<Reviews> getReviewByCourseId(int id, int page) {
        return this.reviewsRepository.getReviewByCourseId(id, page);
    }

    @Override
    public long countReviews(int courseId) {
        return this.reviewsRepository.countReviews(courseId);
    }

    @Override
    public void addReview(Reviews r, Users u) {
        this.reviewsRepository.addReview(r, u);
    }
}
