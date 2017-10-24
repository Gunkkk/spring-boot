package com.course.borrower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 84074 on 2017/10/24.
 */
@Entity
@Table(name = "LOSEBOOK")
public class Losebook {
    @GeneratedValue
    @Id
    @Column(name = "ID")
    private int id;
    @Temporal(TemporalType.DATE)
    private Date losedate;
    @Column(name = "BORROWER_ID")
    private int borrowerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLosedate() {
        return losedate;
    }

    public void setLosedate(Date losedate) {
        this.losedate = losedate;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
}
