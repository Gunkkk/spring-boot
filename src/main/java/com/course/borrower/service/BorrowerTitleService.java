package com.course.borrower.service;

import com.course.admin.entity.Borrower;
import com.course.admin.service.ValidateService;
import com.course.borrower.entity.Loan;
import com.course.borrower.entity.Title;
import com.course.borrower.repository.LoanJPA;
import com.course.borrower.repository.TitleJPA;
import com.course.libraryAdmin.service.ItemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ItemAdminService itemAdminService;
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
     * 验证该都这是否可以预定
     * @param borrower
     * @param libraryCode
     * @return
     */
    public Map<String,String> validateReservation(Borrower borrower,String libraryCode){
        Map<String,String> result = new HashMap<>();
        String msg=null;
        if(!validateService.checkOutOfNum(borrower)){
            msg=msg+"借书数达到\n";
        }
        if(validateService.checkUndue(borrower)){
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
}
