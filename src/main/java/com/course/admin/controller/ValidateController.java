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
}
