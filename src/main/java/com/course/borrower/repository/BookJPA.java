package com.course.borrower.repository;

import com.course.borrower.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * Created by 84074 on 2017/10/23.
 */
@Repository
public interface BookJPA extends JpaRepository<Book,Integer> {

     Page<Book> findByIdOrNameLikeOrAuthorOrIsbn(Integer id, String name, String author, String isbn, org.springframework.data.domain.Pageable pageable);
     List<Book> findByIdOrNameLikeOrAuthorOrIsbn(Integer id, String name, String author, String isbn);

     // @Modifying
   /* @Query("update Book b set b.")
    public void updateBookById(String name, String author, String borrowedNumber, String isbn, float price, int totalNumber,
                               String press, @Temporal(TemporalType.DATE) Date publishDate, String version);*/
}
