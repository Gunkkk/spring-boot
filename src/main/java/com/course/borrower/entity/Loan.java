package com.course.borrower.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 84074 on 2017/10/24.
 */
@Entity
@Table(name = "LOAN")
public class Loan {

    @GeneratedValue
    @Id
    private int id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loandate;
    @Column(name = "BORROWER_ID")
    private int borrowerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "M_DUEDATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Duedate duedate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoandate() {
        return loandate;
    }

    public void setLoandate(Date loandate) {
        this.loandate = loandate;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Duedate getDuedate() {
        return duedate;
    }

    public void setDuedate(Duedate duedate) {
        this.duedate = duedate;
    }
}
