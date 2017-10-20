package com.course.admin.service;

import com.course.admin.entity.AdministratorEntity;
import com.course.admin.repository.AdministratorJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by 84074 on 2017/10/19.
 */
public class UserService implements UserDetailsService {
    @Autowired
    AdministratorJPA administratorJPA;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        AdministratorEntity admin =  administratorJPA.findByUsername(s);
        if (admin==null)
            throw new UsernameNotFoundException("not found!");
        else
            return admin;
    }
}
