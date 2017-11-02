package com.course.admin.service;

import com.course.admin.entity.Borrower;
import com.course.admin.repository.BorrowerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperateService {
    @Autowired
    BorrowerJPA borrowerJPA;

    public Page<Borrower> findAll(Pageable page){ return borrowerJPA.findAll(page);}

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


    public List<Borrower> findAll() {
        List<Borrower> borrower = borrowerJPA.findAll();
        showList(borrower);
        return borrower;
    }

    public List<Borrower> findById(int id) {
        List<Borrower> borrower = borrowerJPA.findById(id);
        showList(borrower);
        return borrower;
    }

    public List<Borrower> findByUsername(String username) {
        List<Borrower> borrower = borrowerJPA.findByUsername(username);
        showList(borrower);
        return borrower;
    }

    public List<Borrower> findByCardNo(String cardNo) {
        List<Borrower> borrower = borrowerJPA.findByCardNo(cardNo);
        showList(borrower);
        return borrower;
    }

    public List<Borrower> findByDepartment(String department) {
        List<Borrower> borrower = borrowerJPA.findByDepartment(department);
        showList(borrower);
        return borrower;
    }

    public List<Borrower> findByType(String type) {
        List<Borrower> borrower = borrowerJPA.findByType(type);
        showList(borrower);
        return borrower;
    }

    @Transactional
    public void updateBorrowerById(String username, String cardNo, String password, String type, String department, int id) {
        borrowerJPA.updateBorrowerById(username,cardNo,password,type,department,id);
    }

    //多条件查询
    public List<Borrower> findByCondition(String username, String cardNo, String type){
        List<Borrower> resultList = null;
        Specification querySpecifi = new Specification<Borrower>() {
            @Override
            public Predicate toPredicate(Root<Borrower> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(null != type){
                    predicates.add(criteriaBuilder.like(root.get("type"), type));
                }
                if(null != cardNo){
                    predicates.add(criteriaBuilder.like(root.get("cardNo"), cardNo));
                }
                if(null != username){
                    predicates.add(criteriaBuilder.like(root.get("username"), "%"+username+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  borrowerJPA.findAll(querySpecifi);
        showList(resultList);
        return resultList;
    }
}
