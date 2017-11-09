package com.course.borrower.repository;

import com.course.borrower.entity.Reservation;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 84074 on 2017/11/9.
 */
public interface ReservationJPA extends JpaRepository<Reservation,Integer> {
        List<Reservation> findByBorrowerId(int borrowerId);
        @Modifying
        @Query("delete from Reservation reservation where reservation.id=?1")
        void delete(int id);
}
