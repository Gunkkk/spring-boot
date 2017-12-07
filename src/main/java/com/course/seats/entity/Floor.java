package com.course.seats.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;

/**
 * Created by 84074 on 2017/12/6.
 */
public class Floor implements Serializable{
    private int floorId;
    private int seatSum;
    private String comment;

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getSeatSum() {
        return seatSum;
    }

    public void setSeatSum(int seatSum) {
        this.seatSum = seatSum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
