package com.course.strategy.repository;

import com.course.strategy.entity.LoanStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanStrategyJPA  extends JpaRepository<LoanStrategy,Integer> {
    LoanStrategy findByType(String type);
}
