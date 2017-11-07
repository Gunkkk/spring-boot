package com.course.admin.repository;

import com.course.admin.entity.Undergraduate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UndergraduateJPA extends JpaRepository<Undergraduate,Integer> {

    @Modifying
    @Query("update Undergraduate u set u.username = ?1, u.cardNo = ?2, u.password= ?3, u.type = ?4,  u.department = ?5, u.major = ?6 where u.id = ?7")
    public void updateUndergraduateById(String username, String cardNo, String password, String type,String department, String major, int id);

    public List<Undergraduate> findAll (Specification querySpecifi);

    public Undergraduate findAllByCardNo (String CardNo);

}
