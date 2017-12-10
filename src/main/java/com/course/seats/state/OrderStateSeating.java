package com.course.seats.state;

import com.course.seats.entity.Yuyue;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class OrderStateSeating implements OrderStateInterface {
    @Override
    public String handle(Yuyue yuyue) {
        if (yuyue.getState().equals("预约中")) {
            getSeated(yuyue);
            return "入座成功";
        }
        return "入座失败";
    }

    private void getSeated(Yuyue yuyue){
        Date seatTime = new Date();
        yuyue.setSeatTime(seatTime);
        yuyue.getSeatStrategies().setUseTime(yuyue);
        yuyue.setState("占领中");
    }
}
