package com.course.admin.repository;

import com.course.admin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/10/19.
 */
public interface UserJPA extends JpaRepository<UserEntity,Integer> {
        public UserEntity findByUsername (String username);

}
