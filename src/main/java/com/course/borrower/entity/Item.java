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
    private int m_Loan;
    private int m_LoseBook;

    private int m_Reservation;
    @Column(name = "TITLE_ID")
    private int titleId;
    @OneToOne
    private Reservation reservation;
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

    public int getM_Loan() {
        return m_Loan;
    }

    public void setM_Loan(int m_Loan) {
        this.m_Loan = m_Loan;
    }

    public int getM_LoseBook() {
        return m_LoseBook;
    }

    public void setM_LoseBook(int m_LoseBook) {
        this.m_LoseBook = m_LoseBook;
    }

    public int getM_Reservation() {
        return m_Reservation;
    }

    public void setM_Reservation(int m_Reservation) {
        this.m_Reservation = m_Reservation;
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
}
