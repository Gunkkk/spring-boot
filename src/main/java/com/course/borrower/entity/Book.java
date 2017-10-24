package com.course.borrower.entity;

import com.sun.xml.internal.ws.developer.Serialization;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 84074 on 2017/10/23.
 */
@Entity
@Table(name = "BOOK")
@PrimaryKeyJoinColumn(name = "ID")
public class Book extends Title {
    @GeneratedValue
    @Id
/*    @OneToOne(targetEntity = Title.class,cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "BOOK_ID")*/
    private int id;

    private String press;
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    private String version;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
