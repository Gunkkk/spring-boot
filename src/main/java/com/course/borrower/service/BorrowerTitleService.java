package com.course.borrower.service;

import com.course.admin.entity.Borrower;
import com.course.admin.service.ValidateService;
import com.course.borrower.entity.*;
import com.course.borrower.repository.*;
import com.course.libraryAdmin.service.ItemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 84074 on 2017/11/8.
 */
@Service
public class BorrowerTitleService {
    @Autowired
    TitleJPA titleJPA;

    @Autowired
    ValidateService validateService;

    @Autowired
    LoanJPA loanJPA;

    @Autowired
    ReservationJPA reservationJPA;

    @Autowired
    ItemAdminService itemAdminService;

    @Autowired
    ItemJPA itemJPA;

    /**
     * search为书名或者作者
     * @param search
     * @return
     */
    public List<Title> search(String search){
        if (search==null){
            return titleJPA.findAll();
        }
        return titleJPA.findByNameLikeOrAuthor("%"+search+"%",search);
    }

    /**
     * 验证该读者是否可以预定
     * @param borrower
     * @return
     */
    public Map<String,String> validateReservation(Borrower borrower){
        Map<String,String> result = new HashMap<>();
        String msg = new String();
        if(!validateService.checkOutOfNum(borrower)){
            msg=msg+"借书数达到\n";
        }
        if(!validateService.checkUndue(borrower)){
            msg=msg+"有书超期未还\n";
        }
        if (msg==null||msg.isEmpty()){
            result.put("result","yes");
        }else{
            result.put("result","no");
            result.put("msg",msg);
        }
        return result;
    }

    /**
     * 返回个人借阅信息
     * @param borrower
     * @return
     */
    public List<Loan> queryLoan(Borrower borrower){
        List<Loan> list = new ArrayList<>();
        list = loanJPA.findByBorrowerId(borrower.getId());
        return list;
    }

    /**
     *
     * @param borrowerId
     * @return
     */
    public List<Reservation> queryReservation(int borrowerId){
        List<Reservation> list = new ArrayList<>();
        list = reservationJPA.findByBorrowerId(borrowerId);
        return list;
    }

    /**
     *删除预定记录
     * @param reservationId
     */
    @Transactional
    public void cancelReservation (int reservationId){
        itemJPA.updateReservation(reservationId);
        reservationJPA.delete(reservationId);
    }

}
