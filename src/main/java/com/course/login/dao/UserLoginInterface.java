package com.course.login.dao;

import com.course.login.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 84074 on 2017/12/4.
 */
@Repository
public interface UserLoginInterface {
    public User findByUsername (@Param("username") String username);
}
