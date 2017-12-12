package com.course.seats.controller;

import com.course.admin.entity.Borrower;
import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.Yuyue;
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
    SeatsInterface seatsInterface;
    @Autowired
    SeatsService seatsService;
    /**
     * 预定座位首页，显示各楼层作为信息
     * 返回座位数和被占座位数
     * @return
     */
    @RequestMapping(value = "/toSeatsIndex.action")
    public ModelAndView toSeatsIndex(){
        ModelAndView modelAndView = new ModelAndView("toSeatsIndex");
//        modelAndView.addObject("floorNum",seatsService.floorNum());
        modelAndView.addObject("floorInfo",seatsService.getFloorInfo());
        return modelAndView;
    }
    @RequestMapping("/toPartsInfo")
    public ModelAndView toPartsInfo(@RequestParam("floorId") Integer floorId){
        ModelAndView modelAndView = new ModelAndView("toPartsIndex");
        modelAndView.addObject("floorInfo",seatsService.getPartsInfo(floorId));
        modelAndView.addObject("floorId",floorId);
        return modelAndView;
    }
//    public String toPartsInfo(@RequestParam("floorId") Integer floorId){
//        return seatsService.getPartsInfo(floorId);
//    }

    /**
     * 进入楼层详情，进行相应楼层的选座
     * 返回该区域各座位状况
     * 座位号row_col
     * @param partId
     * @return
     */
    @RequestMapping(value = "/initSeatsMap")
    public ModelAndView initSeatsMap(@RequestParam("partId") Integer partId,@RequestParam("floorId") Integer floorId){
        ModelAndView modelAndView = new ModelAndView("toPartMap");
        modelAndView.addObject("seatedString",seatsService.getSeatsInfo(partId));
        modelAndView.addObject("partId",partId);
        modelAndView.addObject("floorId",floorId);
        return modelAndView;
    }
//    @RequestMapping(value = "/initSeatsMap")
//    public String initSeatsMap(@RequestParam("partId") Integer partId){
//        return seatsService.getSeatsInfo(partId);
//    }

    /**
     * 选择座位
     * @param row_col
     * @return
     */
    @RequestMapping(value = "/reserveSeats")
    public ModelAndView reserveSeats(@RequestParam("row_col") String row_col,@RequestParam("partId") Integer partId,
                               @RequestParam("floorId") Integer floorId, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Borrower borrower = getBorrower(request);
        Yuyue yuyue = seatsService.getYuyueByStuId(borrower.getId());

        if(yuyue!=null)
        {
            modelAndView.addObject("message","已有预约信息！");
            modelAndView.setViewName("redirect:/showMySeats.action");
        }
        else {
            int stuId = getBorrower(request).getId();
            String type = getBorrower(request).getType();
            String info = seatsService.reserveSeats(row_col, partId, floorId, stuId, type);
            if (info.equals("成功")) {
                modelAndView.setViewName("redirect:/showMySeats.action");
            } else if (info.equals("已被占用")) {
                modelAndView.setViewName("toPartMap");
            } else {
                modelAndView.setViewName("toPartMap");
            }
        }
        return modelAndView;
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
        String msg = seatsService.cancelReservation(getBorrower(request).getId(),partId,floorId);
        return msg;
    }

    /**
     * 校园卡确认坐下
     * 查询卡号与stuId是否对应
     * @param cardNo
     * @return json
     */
    @RequestMapping(value = "/getSeat")
    public ModelAndView getSeat(@RequestParam("cardNo") String cardNo){
        ModelAndView modelAndView = new ModelAndView("redirect:/showMySeats.action");
        String json = seatsService.getSeat(cardNo);
        modelAndView.addObject("json",json);
        return modelAndView;
    }
//    public String getSeat(@RequestParam("cardNo") String cardNo){
//        String json = seatsService.getSeat(cardNo);
//        return json;
//    }

    /**
     * 释放座位
     * 根据卡号查询id然后操作
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/releaseSeatByAdmin")
    public String releaseSeatByAdmin(@RequestParam("cardNo") String cardNo){
        return seatsService.realseSeat(cardNo);
    }
    /**
     * 释放座位
     * 根据卡号查询id然后操作
     * @param
     * @return
     */
    @RequestMapping(value = "/releaseSeatByStu")
    public String releaseSeatByStu(@RequestParam("partId") Integer partId,
                                   @RequestParam("floorId") Integer floorId,HttpServletRequest request){
        return seatsService.realseSeat(getBorrower(request).getId(),partId,floorId);
    }
//    public String releaseSeatByStu(@RequestParam("partId") Integer partId,
//            @RequestParam("floorId") Integer floorId,HttpServletRequest request){
//        return seatsService.realseSeat(getBorrower(request).getId(),partId,floorId);
//    }
    /**
     * Sat Dec 09 19:56:54 CST 201
     * 传出的时间是这种格式
     * 展示我的座位的状态
     * 显示座位信息
     * @return 跳转页面
     */
    @RequestMapping(value = "/showMySeats.action")
    public ModelAndView showMySeats(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("showMySeats");
        Borrower borrower = getBorrower(request);
        Yuyue yuyue = seatsService.getYuyueByStuId(borrower.getId());
        modelAndView.addObject("borrower",borrower);
        if(yuyue!=null){
            int partId = seatsInterface.getPartIdBySeatId(yuyue.getSeatId());
            int floorId = seatsInterface.getFloorIdByPartId(partId);
            modelAndView.addObject("msg","success");
            modelAndView.addObject("partId",partId);
            modelAndView.addObject("floorId",floorId);
            modelAndView.addObject("yuyue",yuyue);
        }else{
            modelAndView.addObject("msg","没有座位信息");
        }
        return modelAndView;
    }


    /**
     * 续时
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/continueSeat")
//    public ModelAndView continueSeat(@RequestParam("cardNo") String cardNo){
//        ModelAndView modelAndView = new ModelAndView("redirect:/showMySeats.action");
//        String json = seatsService.continueSeat(cardNo);
//        modelAndView.addObject("json",json);
//        return modelAndView;
//    }
    public String continueSeat(@RequestParam("cardNo") String cardNo){
        return seatsService.continueSeat(cardNo);
    }


    private Borrower getBorrower(HttpServletRequest request){
        HttpSession session = request.getSession();
        Borrower borrower = (Borrower) session.getAttribute("borrower");
        return borrower;
    }
}