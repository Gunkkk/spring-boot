package com.course.libraryAdmin.service;

import com.course.admin.entity.Borrower;
import com.course.admin.repository.BorrowerJPA;
import com.course.borrower.entity.*;
import com.course.borrower.repository.BookJPA;
import com.course.borrower.repository.ItemJPA;
import com.course.borrower.repository.TitleJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 84074 on 2017/11/5.
 */
@Service
public class ItemAdminService {
    @Autowired
    ItemJPA itemJPA;
    @Autowired
    TitleJPA titleJPA;
    @Autowired
    BorrowerJPA borrowerJPA;


    /**
     * 新增书项
     * 每个书项对应一个书目
     * @param libraryCode
     * @param titleId
     */
    public void addItem(String libraryCode , int titleId){
            Item item = new Item();
            item.setLibraryCode(libraryCode);
            Title title = titleJPA.findOne(titleId);
            int curTotalNum = title.getTotalNumber();
            title.setTotalNumber(curTotalNum+1);
            title.getItems().add(item);
            titleJPA.save(title);
    }

    /**
     * 删除书项
     * @param itemId
     * @param titleId
     */
    public void deleteItem(int itemId , int titleId){
            itemJPA.delete(itemId);
            Title title = titleJPA.findOne(titleId);
            int curTotalNum = title.getTotalNumber();
            title.setTotalNumber(curTotalNum-1);
            titleJPA.save(title);
    }

    /**
     * 输入借阅证号和书籍编码进行借阅
     * 添加借阅记录，借阅时间
     * 修改书项信息、借书人信息
     * 根据借阅人权限添加还书期限
     * @param cardNo
     * @param libraryCode
     */
    public void loanBook(String cardNo, String libraryCode){
        Borrower borrower = borrowerJPA.findByCardNo(cardNo);
        Item item = itemJPA.findByLibraryCode(libraryCode);
       // 获取title信息
        Title title = item.getTitle();
        Loan loan = new Loan();
        Duedate duedate= new Duedate();
        Date d = new Date();
        loan.setLoandate(d);
        d.setMonth(d.getMonth()+1);

        duedate.setDuedate(d);
        loan.setDuedate(duedate);
        loan.setBorrowerId(borrower.getId());
        item.setLoan(loan);
    }

    /**
     * 还书
     * @param cardNo
     * @param libraryCode
     */
    public void returnBook(int cardNo, int libraryCode){

    }
}
