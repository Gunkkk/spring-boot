package com.course.admin.service;

import com.course.admin.entity.Graduate;
import com.course.admin.repository.GraduateJPA;
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
public class GraduateService {

    @Autowired
    GraduateJPA graduateJPA;

    //using for test
    public void showList(List<Graduate> graduate) {
        for (int i = 0; i < graduate.size(); i++) {
            String str = graduate.get(i).toString();
            System.out.println(str);
        }
    }

    public void save(Graduate graduate) {
        graduateJPA.save(graduate);
    }

    public void delete(int id) {
        graduateJPA.delete(id);
    }

    @Transactional
    public void updateGraduateById(int id, String username, String cardNo, String password, String type,
                                   String department, String major, String director)
    {
        graduateJPA.updateGraduateById(username,cardNo,password,type,department,major,director,id);
    }

    public List<Graduate> findAll() {
        List<Graduate> graduate = graduateJPA.findAll();
        return graduate;
    }

//    public Graduate findAllByCardNo(String CardNo) {
//        Graduate graduate = graduateJPA.findAllByCardNo(CardNo);
//        return graduate;
//    }

    //多条件查询
    public List<Graduate> findGraduateByCondition(String username, String cardNo, String password,
                                                  String department, String major, String director){
        List<Graduate> resultList = null;
        Specification querySpecifi = new Specification<Graduate>() {
            @Override
            public Predicate toPredicate(Root<Graduate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

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
                if(null != director){
                    predicates.add(criteriaBuilder.like(root.get("director"), "%"+director+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  graduateJPA.findAll(querySpecifi);
        showList(resultList);
        return resultList;
    }
}
