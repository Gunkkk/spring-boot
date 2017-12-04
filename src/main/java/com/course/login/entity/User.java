package com.course.login.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 84074 on 2017/10/19.
 */
@Entity
@Table(name="USER")
public class User implements Serializable{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String username;
    private String password;

    public User() {

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

}
