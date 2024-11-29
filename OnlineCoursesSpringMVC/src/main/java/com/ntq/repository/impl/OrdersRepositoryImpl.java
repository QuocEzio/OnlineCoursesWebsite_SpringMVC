/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Orders;
import com.ntq.repository.OrdersRepository;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Repository
@Transactional
public class OrdersRepositoryImpl implements OrdersRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public void addOrder(String username, Long total) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Orders order = new Orders();
        order.setUsername(username);
         order.setTotalAmount(total);
        order.setStatus("waitAdmin");
        order.setCreatedAt(new Date());
        session.save(order);
        
    }

    @Override
    public List<Orders> getListOrders() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
        Root root = q.from(Orders.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void acceptOrder(int id) {
        
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Orders o = this.getOrder(id);
        o.setStatus("succeed");
        o.setUpdatedAt(new Date());
        s.save(o);
        
    }

    @Override
    public Orders getOrder(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Orders.class, id);
    }
    
}
