package com.course.admin.repository;

import com.course.admin.entity.Borrower;
import com.course.admin.entity.Graduate;
import com.course.admin.entity.Undergraduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * Created by Yan on 2017/10/27.
 */
public interface BorrowerJPA extends JpaRepository<Borrower,Integer> {

    //增加
    public void save (Graduate graduate);
    public void save (Undergraduate undergraduate);

    //删除
    public void delete(int id);

    //修改
    @Modifying
    @Query("update Borrower b set b.username = ?1, b.cardNo = ?2, b.password= ?3, b.type = ?4,  b.department = ?5 where b.id = ?6")
    public void updateBorrowerById(String username, String cardNo, String password, String type,String department, int id);

    //条件查询
    //解析方法名创建查询
    public List<Borrower> findByUsername (String username);
    public List<Borrower> findById (int id);
    public List<Borrower> findByCardNo (String cardNo);
    public List<Borrower> findByDepartment (String department);
    public List<Borrower> findByType (String type);


}
