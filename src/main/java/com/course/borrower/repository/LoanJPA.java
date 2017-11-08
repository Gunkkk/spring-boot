package com.course.borrower.repository;

import com.course.borrower.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 84074 on 2017/11/6.
 */
public interface LoanJPA extends JpaRepository<Loan,Integer> {
    List<Loan> findByBorrowerId(int id);
}
