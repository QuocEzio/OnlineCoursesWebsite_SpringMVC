/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Cart;
import com.ntq.pojo.Enrollments;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface EnrollmentsService {
    void createEnrollFormCart (List<Cart> carts);
    List<Enrollments> getListEnroll();
}