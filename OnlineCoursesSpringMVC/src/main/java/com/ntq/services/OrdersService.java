/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Orders;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface OrdersService {
    void addOrder(String username, Long total);
    List<Orders> getListOrders();
    void acceptOrder(int id);
    Orders getOrder(int id);
}
