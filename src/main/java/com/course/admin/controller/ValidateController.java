package com.course.admin.controller;

import com.course.admin.entity.Borrower;
import com.course.admin.service.ValidateService;
import com.course.borrower.entity.Item;
import com.course.borrower.entity.Reservation;
import com.course.borrower.service.BorrowerTitleService;
import com.course.libraryAdmin.service.ItemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ValidateController {

    @Autowired
    ValidateService validateService;
    @Autowired
    ItemAdminService itemAdminService;
    @Autowired
    BorrowerTitleService borrowerTitleService;

    @RequestMapping(value = "/checkCardNo.action")
    public String checkCardNo(@RequestParam("cardNo") String cardNo){
        Borrower borrower = validateService.checkCardNo(cardNo);
        if(borrower == null)
        {
            return "未找到用户";
        }
        else
        {
            //检查借阅者借阅的图书是否超过了规定的数量
            if(!validateService.checkOutOfNum(borrower))
            {
                return "借书超数";
            }
            //检查借阅者是否有超过规定借阅期限而未归还的图书
            else if(!validateService.checkUndue(borrower))
            {
                return "有图书过期未还";
            }
            else
            {
                return "验证通过";
            }
        }
    }

    @RequestMapping(value = "/checkCanLoan.action")
    public String checkCanLoan(@RequestParam("libraryCodeLoan") String libraryCodeLoan,
                               @RequestParam("cardNo") String cardNo){

        String flag = new String();

        if(!itemAdminService.isExist(libraryCodeLoan))
        {
            flag = "不存在";
            return flag;
        }

        Borrower borrower = validateService.checkCardNo(cardNo);
        int borrowerId = borrower.getId();
        List<Reservation> reservationList = borrowerTitleService.queryReservation(borrowerId);
        for(Reservation reservation:reservationList)
        {
            List<Item> itemList = reservation.getTitle().getItems();
            for(Item item:itemList)
            {
                if(libraryCodeLoan.equals(item.getLibraryCode()))
                {
                    flag = "已预约";
                    return flag;
                }
            }
        }

        if(itemAdminService.isAvailable(libraryCodeLoan))
        {
            flag = "可借阅";
            return flag;
        }

        flag = "已借出或已被预约";
        return flag;
    }
}
