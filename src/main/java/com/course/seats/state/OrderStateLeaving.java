package com.course.seats.state;

import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.Yuyue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class OrderStateLeaving implements OrderStateInterface {
    @Autowired
    SeatsInterface seatsInterface;
    @Override
    public String handle(Yuyue yuyue) {
        if (yuyue.getState() == "预约中") {
            yuyue.setState("已失效");
            yuyue.setVainTime(new Date());
            seatsInterface.removeSeatOrder(yuyue.getSeatId());
            return "取消预约成功";
        }
        if (yuyue.getState() == "占领中"){
            yuyue.setState("已失效");
            yuyue.setVainTime(new Date());
            return "成功释放座位";
        }
        return "失败";
    }
}
