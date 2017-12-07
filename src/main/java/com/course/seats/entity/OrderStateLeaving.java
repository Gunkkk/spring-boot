package com.course.seats.entity;

import com.course.seats.entity.OrderStateInterface;
import com.course.seats.entity.Yuyue;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class OrderStateLeaving implements OrderStateInterface {
    @Override
    public void handle(Yuyue yuyue) {
        yuyue.setState("已失效");
        yuyue.setVainTime(new Date());
    }
}
