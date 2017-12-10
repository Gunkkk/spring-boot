package com.course;

import com.course.seats.entity.Yuyue;
import com.course.seats.service.SeatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 84074 on 2017/12/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatsTest {

    @Autowired
    SeatsService seatsService;


    @Test
    public void getFloorInfo(){
        System.out.print(seatsService.getFloorInfo().toString()+"===========");
    }
    @Test
    public void getPartsInfo(){
        System.out.println(seatsService.getPartsInfo(3)+"======");
        System.out.println(seatsService.getSeatsInfo(5)+"=====");
    }
    @Test
    public void testReserveSeats(){
        String msg = seatsService.reserveSeats("5_5",5,3,2,"undergraduate");
        System.out.print(msg+"================");
    }

    @Test
    public void cancelReservation(){
        System.out.print(seatsService.cancelReservation(1,1,1)+"============");
    }

    @Test
    public void getSeat(){
        System.out.print(seatsService.getSeat("2")+"=========");
    }

    @Test
    public void continueSeat(){
        System.out.print(seatsService.continueSeat("2")+"==========");
    }
    @Test
    public void showMySeat(){
        Yuyue yuyue = seatsService.getYuyueByStuId(1);
        System.out.println(yuyue.getOrderId());
        System.out.println(yuyue.getDeadTime()+"==========");
    }
    @Test
    public void releaseSeat1(){

        System.out.print(seatsService.realseSeat("2")+"===");
    }

    @Test
    public void releaseSeat2(){

        System.out.print(seatsService.realseSeat(2,5,3)+"=======");
    }
}
