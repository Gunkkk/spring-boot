package com.course.seats.state;

import com.course.seats.entity.Yuyue;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/8.
 */
public class OrderStateContinuing implements OrderStateInterface{
    @Override
    public String handle(Yuyue yuyue) {
        if (yuyue.getState().equals("占领中")){
            yuyue.getSeatStrategies().setContinueTime(yuyue);
            return "续座成功";
        }
        if (yuyue.getState().equals("预约中"))
            return "请先入座";
        return "错误";
    }
}
