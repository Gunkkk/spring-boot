package com.course.strategy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yanyufeng on 2017/11/06.
 */
@Entity
@Table(name = "LOAN_STRATEGY")
public class LoanStrategy {
    @GeneratedValue
    @Id
    private int id;

    //借阅时长(天)
    private int loanDuration = 30;
    //借阅书本数量(本)
    private int loanNumber = 30;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }
}
