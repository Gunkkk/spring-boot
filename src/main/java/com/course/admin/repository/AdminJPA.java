package com.course.admin.repository;

import com.course.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJPA extends JpaRepository<Admin,Integer> {
    public Admin findByUsername (String username);
}
