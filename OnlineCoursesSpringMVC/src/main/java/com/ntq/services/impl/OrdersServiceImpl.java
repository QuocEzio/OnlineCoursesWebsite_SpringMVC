/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Orders;
import com.ntq.repository.OrdersRepository;
import com.ntq.services.OrdersService;
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
public class OrdersServiceImpl implements OrdersService{
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public void addOrder(String username, Long total) {
       this.ordersRepository.addOrder(username, total);
    }

    @Override
    public List<Orders> getListOrders() {
       return this.ordersRepository.getListOrders();
    }

    @Override
    public void acceptOrder(int id) {
        this.ordersRepository.acceptOrder(id);
    }

    @Override
    public Orders getOrder(int id) {
        return this.ordersRepository.getOrder(id);
    }
}
