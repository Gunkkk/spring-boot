package com.course.admin.repository;

import com.course.admin.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/10/19.
 */
public interface AdministratorJPA extends JpaRepository<AdministratorEntity,Integer> {
        public AdministratorEntity findByUsername (String username);
}
