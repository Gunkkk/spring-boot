package com.course.seats.entity;

/**
 * Created by 84074 on 2017/12/7.
 */
public class Seatpart {
    private int partId;
    private int floorId;
    private int seatSum;
    private String partName;
    private String comment;

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
