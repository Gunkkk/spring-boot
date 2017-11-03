package com.course.borrower.repository;

import com.course.borrower.entity.Magazine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 84074 on 2017/10/27.
 */
public interface MagazineJPA extends JpaRepository<Magazine,Integer> {
    Page<Magazine> findByIdOrNameLikeOrAuthorOrIsbn(int id, String name, String author, String isbn, Pageable pageable);
    List<Magazine> findByIdOrNameLikeOrAuthorOrIsbn(int id, String name, String author, String isbn);

}
