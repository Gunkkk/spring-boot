package com.course.admin.service;

import com.course.admin.entity.User;
import com.course.admin.repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanYufeng on 2017/11/07.
 */
@Service
public class UserService {
    @Autowired
    UserJPA userJPA;

    public void save(User user) {
        userJPA.save(user);
    }

    public void delete(int id) {
        userJPA.delete(id);
    }

    public List<User> findAll() {
        List<User> user = userJPA.findAll();
        return user;
    }
}
