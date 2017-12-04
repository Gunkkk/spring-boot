package com.course.login.service;

import com.course.login.dao.AdminLoginInterface;
import com.course.login.dao.UserLoginInterface;
import com.course.login.entity.Admin;
import com.course.admin.entity.Borrower;
import com.course.login.entity.User;
import com.course.login.repository.AdminJPA;
import com.course.admin.repository.BorrowerJPA;
import com.course.login.repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 84074 on 2017/10/22.
 */
@Service
@CacheConfig(cacheNames = "loginuser")
public class LoginService {
    @Autowired
    UserJPA UserJPA;
    @Autowired
    AdminJPA adminJPA;
    @Autowired
    BorrowerJPA borrowerJPA;
    @Autowired
    AdminLoginInterface adminLoginInterface;
    @Autowired
    UserLoginInterface userLoginInterface;

    @Cacheable
    public Map<String, Object> login(String userName, String password, String role) {
        Map<String, Object> map = new HashMap<String, Object>();

        if(role.equals("user")) {
            User user = userLoginInterface.findByUsername(userName);
            if (user == null) {
                map.put("result", "fail");
                map.put("msg", "图书管理员不存在");
            } else {
                if (!user.getPassword().equals(password)) {
                    map.put("result", "fail");
                    map.put("msg", "密码不正确");
                } else {
                    map.put("result", "successUser");
                    map.put("user", user);
                }
            }
        }
        else if(role.equals("admin")) {
            Admin admin = adminLoginInterface.findByUsername(userName);
            if (admin == null) {
                map.put("result", "fail");
                map.put("msg", "系统管理员不存在");
            } else {
                if (!admin.getPassword().equals(password)) {
                    map.put("result", "fail");
                    map.put("msg", "密码不正确");
                } else {
                    map.put("result", "successAdmin");
                    map.put("user", admin);
                }
            }
        }
        return map;
    }

    public Map<String,Object> borrowerLogin(String username,String password){
        Map<String,Object> map = new HashMap<String,Object>();
        Borrower borrower = borrowerJPA.findByUsername(username);
        if (borrower==null){
            map.put("result", "fail");
            map.put("msg", "用户不存在");
            return map;
        }else{
            if (!borrower.getPassword().equals(password)){
                map.put("result", "fail");
                map.put("msg", "密码不正确");
                return map;
            }else{
                map.put("result", "success");
                map.put("borrower", borrower);
                return map;
            }
        }
    }
}
