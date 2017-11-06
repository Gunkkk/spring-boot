package com.course.borrower.entity;

import com.course.config.entity.LoanStrategy;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private Date loandate;
    @Column(name = "BORROWER_ID")
    private int borrowerId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "M_DUEDATE")
    private Duedate duedate;
    public int getId() {
        return id;
    }

//    //Loan() made by YYF for temporary usage.
//    Loan()
//    {
//        LoanStrategy loanStrategy = new LoanStrategy();
//
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(loandate);
//        calendar.add(Calendar.DATE,loanStrategy.getLoanDuration());//把日期往后增加一天.正数往后推,负数往前推
//        duedate.setDuedate(calendar.getTime());   //这个时间就是日期往后推一天的结果
//    }

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
