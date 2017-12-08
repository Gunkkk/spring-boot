package com.course.seats.entity;

import com.course.seats.state.*;
import com.course.seats.strategy.SeatStrategiesInterface;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 84074 on 2017/12/6.
 */
public class Yuyue implements Serializable {
    private int orderId;
    private int stuId;
    private int seatId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date seatTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vainTime;
    private String state;//预约中、占领中、已失效
    private OrderStateInterface stateInterface;
    private SeatStrategiesInterface seatStrategies;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getSeatTime() {
        return seatTime;
    }

    public void setSeatTime(Date seatTime) {
        this.seatTime = seatTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Date getVainTime() {
        return vainTime;
    }

    public void setVainTime(Date vainTime) {
        this.vainTime = vainTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SeatStrategiesInterface getSeatStrategies() {
        return seatStrategies;
    }

    public void setSeatStrategies(SeatStrategiesInterface seatStrategies) {
        this.seatStrategies = seatStrategies;
    }

    public void getReserve(){
        stateInterface=new OrderStateReservting();
        stateInterface.handle(this);
    }
    public String getSeats(){
        stateInterface=new OrderStateSeating();
        return stateInterface.handle(this);
    }
    public String releaseSeats(){
        stateInterface=new OrderStateLeaving();
        return stateInterface.handle(this);
    }
    public String continueSeats(){
        stateInterface = new OrderStateContinuing();
        return stateInterface.handle(this);
    }
}
