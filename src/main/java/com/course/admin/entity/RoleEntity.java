package com.course.admin.entity;

import javax.persistence.*;

/**
 * Created by 84074 on 2017/10/19.
 */
@Entity
@Table(name="ROLE")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Integer roleId ;
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
