package com.course.borrower.entity;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reserveDate;
    @Column(name = "BORROWER_ID")
    private int borrowerId;
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

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



    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
