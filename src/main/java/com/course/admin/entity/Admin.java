package com.course.admin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Created by Yan on 2017/10/24.
 */
@Entity
@Table(name="ADMINISTRATOR")
public class Admin {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private int id;
    private String username;
    private String password;
    private int roleId;
    //if roleId == 0, it is a librarian;
    //if roleId == 1, it is a system administrator;

    public Admin() {

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
