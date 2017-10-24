package com.course.borrower.entity;

import com.sun.xml.internal.ws.developer.Serialization;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 84074 on 2017/10/23.
 */
@Entity
@Table(name = "MAGAZINE")
@PrimaryKeyJoinColumn(name = "ID")
public class Magazine extends Title{
    @GeneratedValue(strategy = GenerationType.AUTO)

/*    @Id
    @JoinColumn(name = "MAGAZINE_ID")
    @OneToOne(targetEntity = Title.class,cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn*/
    private int id;

    private String press;
    private String volume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

}
