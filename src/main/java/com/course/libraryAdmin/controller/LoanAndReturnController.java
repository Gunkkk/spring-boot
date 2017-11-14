package com.course.libraryAdmin.controller;

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
public class LoanAndReturnController {

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

        flag = "已借出 或 已被预约 或 已丢失";
        return flag;
    }

    @RequestMapping(value = "/validateLibraryCode.action")
    public String validateLibraryCode(@RequestParam("libraryCode") String libraryCode){
        if(!itemAdminService.isExist(libraryCode)) {
            return "yes";
        }
        else
            return "no";
    }

    @RequestMapping(value = "/toWork.action")
    public ModelAndView toLoan(){
        ModelAndView modelAndView = new ModelAndView("UserWork");
        return modelAndView;
    }
    @RequestMapping(value = "/toReservation.action")
    public ModelAndView toReservation(){
        List<Reservation> list = itemAdminService.findAllReservation();
        ModelAndView modelAndView = new ModelAndView("UserReservation");
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @RequestMapping(value = "/loanItem.action")
    public ModelAndView deleteUndergraduate(@RequestParam("cardNo")String cardNo,
                                            @RequestParam("libraryCodeLoan")String libraryCode){

        ModelAndView modelAndView = new ModelAndView("redirect:/toWork.action");

        itemAdminService.loanBook(cardNo,libraryCode);

        modelAndView.addObject("cardNo",cardNo);
        modelAndView.addObject("libraryCodeLoan",libraryCode);
        return modelAndView;
    }
    @RequestMapping(value = "/checkCompensation.action")
    public String checkCompensation(@RequestParam("libraryCode") String libraryCode){
        String compensation = itemAdminService.checkCompensation(libraryCode);
        return compensation;
    }
    @RequestMapping(value = "/checkLoseCompensation.action")
    public String checkLoseCompensation(@RequestParam("libraryCodeLose") String libraryCode,
                                        @RequestParam("cardNoLose") String cardNoLose){
        String compensation = itemAdminService.checkLoseCompensation(libraryCode,cardNoLose);
        return compensation;
    }
    @RequestMapping(value = "/returnItem.action")
    public ModelAndView returnItem(@RequestParam("libraryCodeReturn")String libraryCode){
        ModelAndView modelAndView = new ModelAndView("redirect:/toWork.action");
        itemAdminService.returnBook(libraryCode);
        return modelAndView;
    }
    @RequestMapping(value = "/loseItem.action")
    public ModelAndView loseItem(@RequestParam("libraryCodeLose")String libraryCode){
        ModelAndView modelAndView = new ModelAndView("redirect:/toWork.action");
        itemAdminService.addLose(libraryCode);
        return modelAndView;
    }
}
