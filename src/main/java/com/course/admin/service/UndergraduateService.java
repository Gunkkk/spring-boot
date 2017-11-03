package com.course.admin.service;

import com.course.admin.entity.Undergraduate;
import com.course.admin.repository.UndergraduateJPA;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UndergraduateService {

    @Autowired
    UndergraduateJPA undergraduateJPA;

    //using for test
    public void showList(List<Undergraduate> undergraduate) {
        for (int i = 0; i < undergraduate.size(); i++) {
            String str = undergraduate.get(i).toString();
            System.out.println(str);
        }
    }

    public void save(Undergraduate undergraduate) {
        undergraduateJPA.save(undergraduate);
    }

    public void delete(int id) {
        undergraduateJPA.delete(id);
    }

    @Transactional
    public void updateGraduateById(String username, String cardNo, String password, String type,
                                   String department, String major, int id)
    {
        undergraduateJPA.updateUndergraduateById(username,cardNo,password,type,department,major,id);
    }

    public List<Undergraduate> findAll() {
        List<Undergraduate> undergraduate = undergraduateJPA.findAll();
        return undergraduate;
    }
    //多条件查询
    public List<Undergraduate> findUndergraduateByCondition(String username, String cardNo, String password,
                                                  String department, String major){
        List<Undergraduate> resultList = null;
        Specification querySpecifi = new Specification<Undergraduate>() {
            @Override
            public Predicate toPredicate(Root<Undergraduate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(null != username){
                    predicates.add(criteriaBuilder.like(root.get("username"), "%"+username+"%"));
                }
                if(null != cardNo){
                    predicates.add(criteriaBuilder.like(root.get("cardNo"), cardNo));
                }
                if(null != password){
                    predicates.add(criteriaBuilder.like(root.get("password"), password));
                }
                if(null != department){
                    predicates.add(criteriaBuilder.like(root.get("department"), "%"+department+"%"));
                }
                if(null != major){
                    predicates.add(criteriaBuilder.like(root.get("major"), "%"+major+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  undergraduateJPA.findAll(querySpecifi);
        showList(resultList);
        return resultList;
    }
}
