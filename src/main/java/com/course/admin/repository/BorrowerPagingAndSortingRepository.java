package com.course.admin.repository;

import com.course.admin.entity.Borrower;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BorrowerPagingAndSortingRepository extends PagingAndSortingRepository<Borrower,Integer> {

}
