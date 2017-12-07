package com.course.seats.controller;

import com.course.admin.entity.Borrower;
import com.course.seats.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 84074 on 2017/12/6.
 */
@RestController
public class SeatsController {

    @Autowired
    SeatsService seatsService;
    /**
     * 预定座位首页，显示各楼层作为信息
     * 返回座位数和被占座位数
     * @return
     */
    @RequestMapping(value = "/toSeatsIndex.action")
    public ModelAndView toSeatsIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("floorNum",seatsService.floorNum());
        modelAndView.addObject("floorInfo",seatsService.getFloorInfo());
        return modelAndView;
    }
    @RequestMapping("/toPartsInfo")
    public String toPartsInfo(@RequestParam("floorId") Integer floorId){
        return seatsService.getPartsInfo(floorId);
    }
    /**
     * 进入楼层详情，进行相应楼层的选座
     * 返回该区域各座位状况
     * 座位号row_col
     * @param partId
     * @return
     */
    @RequestMapping(value = "/initSeatsMap")
    public String initSeatsMap(@RequestParam("partId") Integer partId){
        return seatsService.getSeatsInfo(partId);
    }

    /**
     * 选择座位
     * @param row_col
     * @return
     */
    @RequestMapping(value = "/reserveSeats")
    public String reserveSeats(@RequestParam("row_col") String row_col,@RequestParam("partId") Integer partId,
                               @RequestParam("floorId") Integer floorId,HttpServletRequest request){
        int stuId = getBorrower(request).getId();
        String type = getBorrower(request).getType();
        return seatsService.reserveSeats(row_col,partId,floorId,stuId,type);
    }

    /**
     * 取消预定的座位
     * 根据cardNo查询id
     * 然后根据id查找记录
     * @param
     * @return
     */
    @RequestMapping(value = "/cancelReservation")
    public String cancelReservation(@RequestParam("partId") Integer partId,
                                    @RequestParam("floorId") Integer floorId,HttpServletRequest request){
        seatsService.cancelReservation(getBorrower(request).getId(),partId,floorId);
        return "success";
    }

    /**
     * 校园卡确认坐下
     * 查询卡号与stuId是否对应
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/getSeat")
    public String getSeat(@RequestParam("cardNo") String cardNo){
        return "";
    }

    /**
     * 释放座位
     * 根据卡号查询id然后操作
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/releaseSeat")
    public String releaseSeat(@RequestParam("cardNo") String cardNo){
        return "";
    }

    /**
     * 展示我的座位的状态
     * 显示座位信息
     * @return 跳转页面
     */
    @RequestMapping(value = "/showMySeats")
    public ModelAndView showMySeats( ){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;

    }


    /**
     * 续时
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/continueSeat")
    public String continueSeat(@RequestParam("cardNo") String cardNo){
        return "";
    }

    private Borrower getBorrower(HttpServletRequest request){
        HttpSession session = request.getSession();
        Borrower borrower = (Borrower) session.getAttribute("borrower");
        return borrower;
    }
}
