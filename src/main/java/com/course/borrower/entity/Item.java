package com.course.borrower.entity;

import javax.persistence.*;

/**
 * Created by 84074 on 2017/10/24.
 */
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue
    private int Id;
    private String libraryCode;
/*    private int m_Loan;
    private int m_LoseBook;
    private int m_Reservation;*/
    @Column(name = "TITLE_ID")
    private int titleId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "M_RESERVATION")
    private Reservation reservation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "M_LOSEBOOK")
    private Losebook losebook;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "M_LOAN")
    private Loan loan;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public void setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Losebook getLosebook() {
        return losebook;
    }

    public void setLosebook(Losebook losebook) {
        this.losebook = losebook;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
