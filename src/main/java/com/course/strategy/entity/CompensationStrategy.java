package com.course.strategy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by Yanyufeng on 2017/11/09.
 */
@Entity
@Table(name = "COMPENSATION_STRATEGY")
public class CompensationStrategy {
    @GeneratedValue
    @Id
    private int id;

    //定义实用的项目
    String item;
    //超时返还政策————总赔款=钱*天数
    private double overtime;
    //丢失书项政策————总赔款=钱*本数
    private double lose;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getLose() {
        return lose;
    }

    public void setLose(double lose) {
        this.lose = lose;
    }
}
