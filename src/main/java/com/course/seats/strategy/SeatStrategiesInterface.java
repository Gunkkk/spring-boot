package com.course.seats.strategy;

import com.course.seats.entity.SeatStrategy;
import com.course.seats.entity.Yuyue;

/**
 * Created by 84074 on 2017/12/6.
 */
public interface SeatStrategiesInterface {
    void setReserveTime(Yuyue yuyue);
    void setUseTime(Yuyue yuyue);
    void setContinueTime(Yuyue yuyue);
}
