package com.course.admin.repository;

import com.course.admin.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/11/5.
 */
public interface BorrowerJPA extends JpaRepository<Borrower,Integer> {
    Borrower findByCardNo(String cardNo);
}
