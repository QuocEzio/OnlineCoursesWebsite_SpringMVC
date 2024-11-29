/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.repository.StatsRepository;
import java.util.List;
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
public class StatsRepositoryImpl implements StatsRepository{
    
     @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> getMonthlyRevenue() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "SELECT YEAR(o.updatedAt) as year, " +
                     "MONTH(o.updatedAt) as month, " +
                     "SUM(o.totalAmount) as revenue " +
                     "FROM Orders o " +
                     "GROUP BY YEAR(o.updatedAt), MONTH(o.updatedAt) " +
                     "ORDER BY year DESC, month DESC";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        return query.list();
    }

    
}
