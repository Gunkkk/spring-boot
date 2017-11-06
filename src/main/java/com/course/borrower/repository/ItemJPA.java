package com.course.borrower.repository;

import com.course.borrower.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 84074 on 2017/11/5.
 */
public interface ItemJPA extends JpaRepository<Item,Integer> {
        Item findByLibraryCode(String libraryCode);
}
