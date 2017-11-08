package com.course.borrower.controller;

import com.course.admin.entity.Borrower;
import com.course.borrower.entity.Item;
import com.course.borrower.entity.Loan;
import com.course.borrower.entity.Title;
import com.course.borrower.service.BorrowerTitleService;
import com.course.libraryAdmin.service.ItemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 84074 on 2017/11/8.
 */
@Controller
public class BorrowerTitlesController {
    @Autowired
    BorrowerTitleService borrowerTitleService;
    @Autowired
    ItemAdminService itemAdminService;
    @RequestMapping(value = "/borrowerSearch.action")
    public ModelAndView search(@RequestParam("search") String search){
        ModelAndView modelAndView = new ModelAndView("/searchResult");
        List<Title> list = new ArrayList<>();
        list = borrowerTitleService.search(search);
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    /**
     * 进入准备预定状态
     * @return
     */
    @RequestMapping(value = "/toAddReservation.action")
    public ModelAndView toAddReservation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/toReservation");
        return modelAndView;
    }

    /**前置条件：登录完成
     * 添加预定
     * @param request
     * @param libraryCode
     * @return
     */
    @RequestMapping(value = "/addReservation.action")
    public ModelAndView addReservation(HttpServletRequest request,String libraryCode){
        ModelAndView modelAndView = new ModelAndView();
        Borrower borrower = getBorrower(request);
        Map<String,String> result = borrowerTitleService.validateReservation(borrower,libraryCode);
        if (result.get("result").equals("yes")){
            itemAdminService.addReservation(libraryCode, borrower.getCardNo());
        }
        else modelAndView.addObject("msg",result.get("msg"));
        return modelAndView;
    }

    /**
     * 查询个人借阅情况
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryLoan.action")
    public ModelAndView queryLoan(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/queryLoan");
        Borrower borrower = getBorrower(request);
        List<Loan> list = borrowerTitleService.queryLoan(borrower);
        modelAndView.addObject("list",list);
        return  modelAndView;

    }

    /**
     * 查询该书目对应可以借阅的书项
     * @param titleId
     * @return
     */
    @RequestMapping("/borrowerQueryItem.action")
    public ModelAndView borrowerQueryItem(@RequestParam("titleId") int titleId){
        ModelAndView modelAndView = new ModelAndView("/borrowerQueryItem");
        List<Item> list = itemAdminService.borrowerQueryItem(titleId);
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    private Borrower getBorrower(HttpServletRequest request){
        HttpSession session = request.getSession();
        Borrower borrower = (Borrower) session.getAttribute("borrower");
        return borrower;
    }
}
