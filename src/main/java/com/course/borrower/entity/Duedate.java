package com.course.borrower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 84074 on 2017/10/24.
 */
@Entity
@Table(name = "DUEDATE")
public class Duedate {
    @GeneratedValue
    @Id
    private int id;
    @Temporal(TemporalType.DATE)
    private Date duedate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
}
