package com.course.admin.controller;

import com.course.admin.entity.Borrower;
import com.course.admin.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BorrowerController {

    @Autowired
    OperateService operateService;

//    @RequestMapping(value = "/findAllBorrowers.action")
//    public ModelAndView findAllBorrower(){
//        ModelAndView modelAndView = new ModelAndView();
//
//        int currentPage =0; //当前页从0 开始
//        int pageSize = 5;
//
//        //排序
//        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "id");
//        Sort.Order usernameOrder = new Sort.Order(Sort.Direction.ASC,"username");
//        Sort sort = new Sort(idOrder,usernameOrder);
//        PageRequest pageRequest  = new PageRequest(currentPage, pageSize, sort);
//        Page<Borrower> page = operateService.findAll(pageRequest);
//
////        System.out.println("总记录数:" + page.getTotalElements());
////        System.out.println("总页数:" + page.getTotalPages());
////        System.out.println("当前页（request):" + page.getNumber());
////        System.out.println("当前页总记录数（request):" + page.getSize());
////        System.out.println("当前页记录总数：" + page.getNumberOfElements());
////        List<Borrower> borrowers = page.getContent();
//
//        modelAndView.addObject("page",page);
//
//        modelAndView.setViewName("borrowers");
//        return modelAndView;
//    }
}
