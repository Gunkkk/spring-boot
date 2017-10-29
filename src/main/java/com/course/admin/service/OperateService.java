package com.course.admin.service;

import com.course.admin.entity.Borrower;
import com.course.admin.repository.BorrowerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class OperateService {
    @Autowired
    BorrowerJPA borrowerJPA;


    public void save(Borrower borrower) {
        borrowerJPA.save(borrower);
    }

    public void delete(int id) {
        borrowerJPA.delete(id);
    }

    public void showList(List<Borrower> borrower) {
        for (int i = 0; i < borrower.size(); i++) {
            String str = borrower.get(i).toString();
            System.out.println(str);
        }
    }

    public void findById(int id) {
        List<Borrower> borrower = borrowerJPA.findById(id);
        showList(borrower);
    }

    public void findByUsername(String username) {
        List<Borrower> borrower = borrowerJPA.findByUsername(username);
        showList(borrower);
    }

    public void findByCardNo(String cardNo) {
        List<Borrower> borrower = borrowerJPA.findByCardNo(cardNo);
        showList(borrower);
    }

    public void findByDepartment(String department) {
        List<Borrower> borrower = borrowerJPA.findByDepartment(department);
        showList(borrower);
    }

    public void findByType(String type) {
        List<Borrower> borrower = borrowerJPA.findByType(type);
        showList(borrower);
    }

    @Transactional
    public void updateBorrowerById(String username, String cardNo, String password, String type,String department, int id) {
        borrowerJPA.updateBorrowerById(username,cardNo,password,type,department,id);
    }
}
