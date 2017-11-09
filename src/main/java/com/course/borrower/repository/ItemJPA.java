package com.course.borrower.repository;

import com.course.borrower.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 84074 on 2017/11/5.
 */
public interface ItemJPA extends JpaRepository<Item,Integer> {
        Item findByLibraryCode(String libraryCode);
        List<Item> findByTitleId(int id);
        @Modifying
        @Query("delete from Item item where item.id=?1")
        void deleteById(int id);

        @Modifying
        @Query(value = "update item set m_reservation=null where m_reservation=?1",nativeQuery = true)
        void updateReservation(int reservationId);
}
