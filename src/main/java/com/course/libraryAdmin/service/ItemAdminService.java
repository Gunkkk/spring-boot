package com.course.libraryAdmin.service;

import com.course.admin.entity.Borrower;
import com.course.admin.repository.BorrowerJPA;
import com.course.borrower.entity.*;
import com.course.borrower.repository.*;
import com.course.strategy.repository.CompensationJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Autowired
    LoanJPA loanJPA;
    @Autowired
    CompensationJPA compensationJPA;
    @Autowired
    ReservationJPA reservationJPA;
    /**
     * 新增书项
     * 每个书项对应一个书目
     * @param libraryCode
     * @param titleId
     */
    @Transactional
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
    @Transactional
    public void deleteItem(int itemId , int titleId){
            Title title = titleJPA.findOne(titleId);
            int curTotalNum = title.getTotalNumber();
            title.setTotalNumber(curTotalNum-1);
            titleJPA.save(title);
            itemJPA.deleteById(itemId);
    }

    /**
     * 根据titleid来查询一个书目的所有书项
     * @param titleId
     * @return
     */
    public List<Item> queryItemByTitleId(int titleId){
            List <Item> list = new ArrayList<>();
            list = itemJPA.findByTitleId(titleId);
            return list;
        }

    /**
     * 返回可借阅的书项
     * @param titleId
     * @return
     */
    public List<Item> borrowerQueryItem (int titleId){
        List<Item> result = new ArrayList<>();
        List<Item> list = queryItemByTitleId(titleId);
        for (Item item:list ){
            if(isAvailable(item.getLibraryCode())){
                result.add(item);
            }
        }
        return result;
    }
    /**
     * 输入借阅证号和书籍编码进行借阅
     * 添加借阅记录，借阅时间
     * 修改书项信息、借书人信息
     * 根据借阅人权限添加还书期限
     * @param cardNo
     * @param libraryCode
     */
    @Transactional
    public void loanBook(String cardNo, String libraryCode){
        Borrower borrower = borrowerJPA.findByCardNo(cardNo);
        Item item = itemJPA.findByLibraryCode(libraryCode);
       // 获取title信息
        Title title = item.getTitle();
        title.setBorrowedNumber(title.getBorrowedNumber()+1);
        Loan loan = new Loan();
        Duedate duedate= new Duedate();
        Date loandate = new Date();
        Date due = new Date();
        loan.setLoandate(loandate);
        due.setMonth(due.getMonth()+1);

        duedate.setDuedate(due);
        loan.setDuedate(duedate);
        loan.setBorrowerId(borrower.getId());
        item.setLoan(loan);
        //itemJPA.save(item);
        titleJPA.save(title); //自动修改item
    }

    public int betweenDate(Date nowDate, Date dueDate) {
        long nowDateLong = nowDate.getTime();
        long dueDateLong = dueDate.getTime();
        long betweenDate = (nowDateLong - dueDateLong) / (1000 * 60 * 60 * 24); //计算间隔多少天，则除以毫秒到天的转换公式
        int betweenDateInt = (int)betweenDate;
        return betweenDateInt;
    }
    /**
     * 还书
     * @param libraryCode
     */
    public double checkCompensation(String libraryCode){
        Item item = itemJPA.findByLibraryCode(libraryCode);

        Date currentDate = new Date();
        Date dueDate = item.getLoan().getDuedate().getDuedate();

        String itemType = item.getTitle().getType();

        //如果未过期，返回0
        if(currentDate.before(dueDate)) {
            return 0;
        }
        //如果过期了，则计算赔偿出compensation
        else
        {
            int betweenDate = betweenDate(currentDate,dueDate);
            double compensation = betweenDate * compensationJPA.findByItem(itemType).getOvertime();
            return compensation;
        }
    }

    /**
     * 还书
     * @param libraryCode
     */
    @Transactional
    public void returnBook( String libraryCode){
        Item item = itemJPA.findByLibraryCode(libraryCode);
        Title title = item.getTitle();
        title.setBorrowedNumber(title.getBorrowedNumber()-1);
        Loan loan = item.getLoan();
        item.setLoan(null);
        itemJPA.save(item);    //删除次序不能改变  外键先删除
        loanJPA.delete(loan.getId());
        titleJPA.save(title);
    }

    /**
     * 书项丢失
     * @param libraryCode
     */
    @Transactional
    public void addLose(String libraryCode){
        Item item = itemJPA.findByLibraryCode(libraryCode);
        Losebook losebook = new Losebook();
        losebook.setBorrowerId(item.getLoan().getBorrowerId());
        losebook.setLosedate(new Date());
        item.setLosebook(losebook);
        itemJPA.save(item);
    }

    /**
     * 预定书
     * @param libraryCode
     * @param cardNo
     */
    @Transactional
    public void addReservation(String libraryCode,String cardNo){
        Borrower borrower = borrowerJPA.findByCardNo(cardNo);
        Item item = itemJPA.findByLibraryCode(libraryCode);
        Title title = item.getTitle();
        Reservation reservation = new Reservation();
        reservation.setBorrowerId(borrower.getId());
        reservation.setReserveDate(new Date());
        reservation.setTitle(title);
        item.setReservation(reservation);
        title.setBorrowedNumber(title.getBorrowedNumber()+1);
     //   title.getReservations().add(reservation);
        titleJPA.save(title);

    }
    @Transactional
    public List<Reservation> findAllReservation(){
        List<Reservation> list = new ArrayList<>();
        list = reservationJPA.findAll();
        return list;
    }

    /**BorrowerTitleService中实现了
     * 可以删除预定
     */
    public void cancelReservation(int reservationId){


    }
    /**
     * 是否存在
     * @param libraryCode
     * @return
     */
    public boolean isExist (String libraryCode){
        Item item = itemJPA.findByLibraryCode(libraryCode);
        if (item!=null){
            return true;
        }
        return false;
    }
    public boolean isAvailable(String libraryCode){
        Item item = itemJPA.findByLibraryCode(libraryCode);
        if (item.getLoan()!=null||item.getLosebook()!=null||item.getReservation()!=null)
            return false;
        return true;
    }
}
