package com.course;



import com.course.admin.entity.*;
import com.course.admin.service.GraduateService;
import com.course.admin.service.ValidateService;
import com.course.borrower.entity.Item;
import com.course.borrower.entity.Reservation;
import com.course.borrower.repository.ItemJPA;
import com.course.borrower.service.BorrowerTitleService;
import com.course.libraryAdmin.service.ItemAdminService;
import com.course.strategy.repository.CompensationJPA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServicesTest {

    @Autowired
    GraduateService graduateService;
    @Autowired
    ValidateService validateService;
    @Autowired
    ItemAdminService itemAdminService;
    @Autowired
    ItemJPA itemJPA;
    @Autowired
    CompensationJPA compensationJPA;
    @Autowired
    BorrowerTitleService borrowerTitleService;

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

    @Test
    public void betweenDate() {
        Item item = itemJPA.findByLibraryCode("111");
        Date currentDate = new Date();
        Date dueDate = item.getLoan().getDuedate().getDuedate();

        String itemType = item.getTitle().getType();

        //如果未过期，返回0
        if (currentDate.before(dueDate)) {
            System.out.println("0000000000000000000000000");
        }
        //如果过期了，则计算赔偿出compensation
        else {
            int betweenDate = itemAdminService.betweenDate(currentDate, dueDate);
            double compensation = betweenDate * compensationJPA.findByType(itemType).getOvertime();
            System.out.println(compensation);
        }

    }

    @Test
    public void checkCanLoan() {
        String cardNo = "004";
        String libraryCodeLoan = "111";
        Borrower borrower = validateService.checkCardNo(cardNo);
        int borrowerId = borrower.getId();

        List<Reservation> reservationList = borrowerTitleService.queryReservation(borrowerId);
        System.out.println("*************************************************");

        if(!itemAdminService.isExist(libraryCodeLoan))
        {
            System.out.println("不存在");
        }

        if(reservationList != null) {
            for (Reservation reservation : reservationList) {
                List<Item> itemList = reservation.getTitle().getItems();
                for (Item item : itemList) {
                    if (libraryCodeLoan.equals(item.getLibraryCode())) {
                        System.out.println("已预约");
                    }
                }
            }
        }

        if(itemAdminService.isAvailable(libraryCodeLoan))
        {
            System.out.println("可借阅");
        }

        System.out.println("已借出或已被预约");
    }

}