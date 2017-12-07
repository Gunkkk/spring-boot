package com.course.seats.entity;

import java.io.Serializable;

/**
 * Created by 84074 on 2017/12/6.
 */
public class Seat implements Serializable{
    private int seatId;
    private int partId;
    private int orderId;
    private String rowCol;
    private String comment;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }


    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRowCol() {
        return rowCol;
    }

    public void setRowCol(String rowCol) {
        this.rowCol = rowCol;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
