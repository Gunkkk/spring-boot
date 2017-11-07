package com.course;



import com.course.admin.entity.*;
import com.course.admin.service.GraduateService;
import com.course.admin.service.ValidateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServicesTest {

    @Autowired
    GraduateService graduateService;
    @Autowired
    ValidateService validateService;

    @Test
    public void findGraduateByCondition(){
        graduateService.findGraduateByCondition(null,null,"123456",null,null,null);
        List<Graduate> GraduateListFound =
                graduateService.findGraduateByCondition(null,null,null,null,null,null);
        graduateService.showList(GraduateListFound);
    }

    @Test
    public void validation(){
        String cardNo = "002";
        Borrower borrower = validateService.checkCardNo(cardNo);
        if(borrower == null)
        {
            System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        }
    }
}