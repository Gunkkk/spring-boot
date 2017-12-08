package com.course.seats.state;

import com.course.seats.entity.Yuyue;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class OrderStateReservting implements OrderStateInterface {
    /**
     * 预约
     * 生成yuyue事项
     * 预约时间、最晚失效时间、状态
     * @param yuyue
     */
    @Override
    public String handle(Yuyue yuyue) {
        Date reserveTime = new Date();
        yuyue.setOrderTime(reserveTime);
        yuyue.getSeatStrategies().setReserveTime(yuyue);
        yuyue.setState("预约中");
        return "预约成功";
    }
}
