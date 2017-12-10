package com.course.admin.entity;

import com.course.borrower.entity.Loan;
import com.course.borrower.entity.Losebook;
import com.course.borrower.entity.Reservation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="BORROWER")
@Inheritance(strategy = InheritanceType.JOINED)
public class Borrower implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column
    private String cardNo;
    private String department;
    private String username;
    private String password;
    private String type;

    @OneToMany(targetEntity = Losebook.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "BORROWER_ID")
    private List<Losebook> losebookList;

    @OneToMany(targetEntity = Reservation.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "BORROWER_ID")
    private List<Reservation> reservationList;

    @OneToMany(targetEntity = Loan.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "BORROWER_ID")
    private List<Loan> loanList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Losebook> getLosebookList() {
        return losebookList;
    }

    public void setLosebookList(List<Losebook> losebookList) {
        this.losebookList = losebookList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", cardNo='" + cardNo + '\'' +
                ", department='" + department + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
