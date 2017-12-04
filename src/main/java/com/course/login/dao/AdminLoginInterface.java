package com.course.login.dao;

import com.course.login.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 84074 on 2017/12/4.
 */
@Repository
public interface AdminLoginInterface {
    public Admin findByUsername (@Param("username") String username);
}

