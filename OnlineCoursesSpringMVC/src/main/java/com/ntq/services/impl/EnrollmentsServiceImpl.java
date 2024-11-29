/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Cart;
import com.ntq.pojo.Enrollments;
import com.ntq.repository.EnrollmentsRepository;
import com.ntq.services.EnrollmentsService;
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
public class EnrollmentsServiceImpl implements EnrollmentsService{

    
    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    @Override
    public void createEnrollFormCart(List<Cart> carts) {
        this.enrollmentsRepository.createEnrollFormCart(carts);
    }

    @Override
    public List<Enrollments> getListEnroll() {
        return this.enrollmentsRepository.getListEnroll();
    }
    
}
