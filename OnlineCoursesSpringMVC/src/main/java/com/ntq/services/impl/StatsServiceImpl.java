/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.repository.StatsRepository;
import com.ntq.services.StatsService;
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
public class StatsServiceImpl implements StatsService{
    
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> getMonthlyRevenue() {
        return this.statsRepository.getMonthlyRevenue();
    }

 
    
}
