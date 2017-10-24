package com.course.borrower.repository;

import com.course.borrower.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/10/23.
 */
public interface BookJPA extends JpaRepository<Book,Integer> {

}
