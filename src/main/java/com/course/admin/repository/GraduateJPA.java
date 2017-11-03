package com.course.admin.repository;

import com.course.admin.entity.Graduate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraduateJPA extends JpaRepository<Graduate,Integer> {

    @Modifying
    @Query("update Graduate g set g.username = ?1, g.cardNo = ?2, g.password= ?3, g.type = ?4, g.department = ?5, g.major = ?6, g.director = ?7 where g.id = ?8")
    public void updateGraduateById(String username, String cardNo, String password, String type,String department, String major, String director, int id);

    public List<Graduate> findAll (Specification querySpecifi);

}
