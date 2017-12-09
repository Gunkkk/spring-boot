package com.course.seats.state;

import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.Yuyue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class OrderStateLeaving implements OrderStateInterface {
    @Override
    public String handle(Yuyue yuyue) {
        if (yuyue.getState().equals("预约中")) {
            yuyue.setState("已失效");
            yuyue.setVainTime(new Date());
            return "取消预约成功";
        }
        if (yuyue.getState().equals("占领中")){
            yuyue.setState("已失效");
            yuyue.setVainTime(new Date());
            return "成功释放座位";
        }
        return "失败";
    }
}
