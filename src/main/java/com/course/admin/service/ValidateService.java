package com.course.admin.service;

import com.course.admin.entity.Borrower;
import com.course.admin.entity.Graduate;
import com.course.admin.entity.Undergraduate;
import com.course.admin.repository.GraduateJPA;
import com.course.admin.repository.UndergraduateJPA;
import com.course.borrower.entity.Loan;
import com.course.strategy.entity.LoanStrategy;
import com.course.strategy.repository.LoanStrategyJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YanYufeng on 2017/11/06.
 *
 * 用于图书管理员在进行借书操作时，验证用户。
 */

@Service
public class ValidateService {

    @Autowired
    GraduateJPA graduateJPA;
    @Autowired
    UndergraduateJPA undergraduateJPA;
    @Autowired
    LoanStrategyJPA loanStrategyJPA;

    //系统验证借阅证是否有效
    public Borrower checkCardNo(String cardNo)
    {
        List<Graduate> graduateList = graduateJPA.findAll();
        for(Graduate graduate:graduateList)
        {
            String cardNo1 = graduate.getCardNo();
            if(cardNo1.equals(cardNo))
            {
                return graduateJPA.findAllByCardNo(cardNo);
            }
        }
        List<Undergraduate> undergraduateList = undergraduateJPA.findAll();
        for(Undergraduate undergraduate:undergraduateList)
        {
            String cardNo2 = undergraduate.getCardNo();
            if(cardNo2.equals(cardNo))
            {
                return undergraduateJPA.findAllByCardNo(cardNo);
            }
        }

        return null;
    }

    //检查借阅者借阅的图书是否超过了规定的数量
    public boolean checkOutOfNum(Borrower borrower){
        System.out.print(borrower.getUsername());
        List<Loan> LoanedBook = new ArrayList<>();
        LoanedBook = borrower.getLoanList();
        int loanNum = LoanedBook.size();
        int maxNum = loanStrategyJPA.findByType("all").getLoanNumber();
        if(loanNum > maxNum)
        {
            return false;
        }
        return true;
    }

    //检查借阅者是否有超过规定借阅期限而未归还的图书
    public boolean checkUndue(Borrower borrower){
        List<Loan> LoanedBook = borrower.getLoanList();
        Date currentDate = new Date();

        for(Loan loanedBook:LoanedBook) {
            Date dueDate = loanedBook.getDuedate().getDuedate();
            if(currentDate.getTime() > dueDate.getTime())
            {
                return false;
            }
        }
        return true;
    }

}
