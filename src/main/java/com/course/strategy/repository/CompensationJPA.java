package com.course.strategy.repository;

import com.course.strategy.entity.CompensationStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompensationJPA extends JpaRepository<CompensationStrategy,Integer> {
    CompensationStrategy findByType(String type);
}
