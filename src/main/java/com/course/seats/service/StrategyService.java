package com.course.seats.service;

import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.SeatStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by 84074 on 2017/12/7.
 */
@Service
@CacheConfig(cacheNames = "strategy")
public class StrategyService {
    @Autowired
    SeatsInterface seatsInterface;

    @Cacheable
    public SeatStrategy getStratey(int floorId,String type){
        return seatsInterface.getStrategy(type,floorId);
    }
}
