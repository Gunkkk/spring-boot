package com.course.borrower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 84074 on 2017/10/24.
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @GeneratedValue
    @Id
    private int id;
    @Temporal(TemporalType.DATE)
    private Date reserveDate;
    private int borrowerId;
    @Column(name = "TITLE_ID")
    private int titleId;



    @OneToOne(mappedBy = "m_Reservation")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }


}
