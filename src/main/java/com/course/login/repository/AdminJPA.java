package com.course.login.repository;

import com.course.login.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJPA extends JpaRepository<Admin,Integer> {
    public Admin findByUsername (String username);
}
