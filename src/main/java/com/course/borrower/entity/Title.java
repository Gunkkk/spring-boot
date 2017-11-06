package com.course.borrower.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 84074 on 2017/10/23.
 */
@Entity
@Table(name = "TITLE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Title {
    @GeneratedValue
    @Id
    private int id;
    private String author;
    private int borrowedNumber;
    private String isbn;
    private String name;
    private float price;
    private int totalNumber;
    private String type;
    @OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="TITLE_ID")
    private List<Item> items;

    @OneToMany(targetEntity = Reservation.class,cascade =  CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private List<Reservation> reservations;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(int borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
