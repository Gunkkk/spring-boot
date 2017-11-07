package com.course.admin.controller;

import com.course.admin.entity.Borrower;
import com.course.admin.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @RequestMapping(value = "/checkCardNo.action")
    public ModelAndView checkCardNo(@RequestParam("cardNo") String cardNo){
        ModelAndView modelAndView = new ModelAndView("UserWork");
        Borrower borrower = validateService.checkCardNo(cardNo);
        String flag = new String();
        if(borrower == null)
        {
            flag = "notFound";
        }
        else
        {
            //检查借阅者借阅的图书是否超过了规定的数量
            if(!validateService.checkOutOfNum(borrower))
            {
                flag = "OutOfNum";
            }
            //检查借阅者是否有超过规定借阅期限而未归还的图书
            else if(!validateService.checkUndue(borrower))
            {
                flag = "undue";
            }
            else
            {
                flag = "pass";
            }
        }

        modelAndView.addObject("flag",flag);
        return modelAndView;
    }
}
