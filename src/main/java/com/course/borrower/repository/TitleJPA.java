package com.course.borrower.repository;

import com.course.borrower.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/10/27.
 */
public interface TitleJPA extends JpaRepository<Title,Integer> {


}
