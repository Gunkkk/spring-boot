package com.course.seats.entity;

import java.io.Serializable;

/**
 * Created by 84074 on 2017/12/6.
 */
public class SeatStrategy implements Serializable {
    private int strategyId;
    private int floorId;
    private int beforeTime;
    private int usingTime;
    private int continueTime;
    private  String type;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(int beforeTime) {
        this.beforeTime = beforeTime;
    }

    public int getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(int usingTime) {
        this.usingTime = usingTime;
    }

    public int getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(int continueTime) {
        this.continueTime = continueTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
