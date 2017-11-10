package com.course.borrower.repository;

import com.course.borrower.entity.Losebook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/11/6.
 */
public interface LosebookJPA extends JpaRepository<Losebook,Integer> {
}
