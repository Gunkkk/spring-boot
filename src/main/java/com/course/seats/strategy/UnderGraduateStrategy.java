package com.course.seats.strategy;

import com.course.seats.entity.SeatStrategy;
import com.course.seats.entity.Yuyue;
import com.course.seats.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class UnderGraduateStrategy implements SeatStrategiesInterface {
    private SeatStrategy seatStrategy;
    private int floorId;

    public UnderGraduateStrategy(int floorId,SeatStrategy seatStrategy){
        this.floorId=floorId;
        this.seatStrategy = seatStrategy;
    }

    @Override
    public void setReserveTime(Yuyue yuyue) {
        Date reserveTime = yuyue.getOrderTime();
        Calendar cd = Calendar.getInstance();
        cd.setTime(reserveTime);
        int min = seatStrategy.getBeforeTime();
        cd.add(Calendar.MINUTE,min);
        yuyue.setDeadTime(cd.getTime());
    }

    @Override
    public void setUseTime(Yuyue yuyue) {
        Date seatTime = yuyue.getSeatTime();
        int min = seatStrategy.getUsingTime();
        Calendar cd = Calendar.getInstance();
        cd.setTime(seatTime);
        cd.add(Calendar.MINUTE,min);
        yuyue.setDeadTime(cd.getTime());
    }

    @Override
    public void setContinueTime(Yuyue yuyue) {
        Date deadTime = yuyue.getSeatTime();
        int min = seatStrategy.getContinueTime();
        Calendar cd = Calendar.getInstance();
        cd.setTime(deadTime);
        cd.add(Calendar.MINUTE,min);
        yuyue.setDeadTime(cd.getTime());

    }
}
