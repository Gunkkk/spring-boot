package com.course.admin.service;

import com.course.admin.repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by YanYufeng on 2017/11/07.
 */
@Service
public class UserService {
    @Autowired
    UserJPA userJPA;


}
