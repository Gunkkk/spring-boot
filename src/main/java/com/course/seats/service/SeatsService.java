package com.course.seats.service;

import com.course.admin.repository.BorrowerJPA;
import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.Floor;
import com.course.seats.entity.SeatStrategy;
import com.course.seats.entity.Seatpart;
import com.course.seats.entity.Yuyue;
import com.course.seats.strategy.GraduateStrategy;
import com.course.seats.strategy.UnderGraduateStrategy;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**！！不同value只是对应不同的缓存策略，
 * 不是如网上大多教程所说的缓存，
 * 所以相同key不同vaule将错误
 * Created by 84074 on 2017/12/6.
 */
@Service
public class SeatsService {
    @Autowired
    SeatsInterface seatsInterface;
    @Autowired
    BorrowerJPA borrowerJPA;
    @Autowired
    StrategyService strategyService;
    /**
     * 选座首页 选择楼层
     * 显示楼层状态 总座位数 被占座位数
     * @return
     */
    public List<Map<String,Integer>> getFloorInfo(){
        List<Map<String,Integer>> floorInfo = new ArrayList<>();
        int seatNum,seatedNum;
        List<Floor> floorList = seatsInterface.getFloors();
        for(Floor i:floorList){
            Map<String,Integer> map = new HashMap<>();
            seatNum = i.getSeatSum();
            seatedNum = seatsInterface.getSeatedNum(i.getFloorId());
            map.put("seatNum",seatNum);
            map.put("seatedNum",seatedNum);
            floorInfo.add(map);
        }
       return floorInfo;
    }

    @Cacheable
    public int floorNum(){
        return seatsInterface.getFloorSNum();
    }

    /**
     * 返回区域信息
     * @param floorId
     * @return
     */
    @Cacheable(value = "parts",key = "'parts_'+#floorId")
    public String getPartsInfo(int floorId){
        List<Map<String,Integer>> partInfo = new ArrayList<>();
        int seatNum,seatedNum;
        List<Seatpart> partList = seatsInterface.getParts(floorId);
        for(Seatpart i:partList){
            Map<String,Integer> map = new HashMap<>();
            seatNum = i.getSeatNum();
            seatedNum = seatsInterface.getPartSeatedNum(i.getPartId());
            map.put("seatNum",seatNum);
            map.put("seatedNum",seatedNum);
            partInfo.add(map);
        }
        return JSONArray.toJSONString(partInfo);
    }

    /**
     * 返回一个区域的座位被占用情况
     * @param partId
     * @return
     */
    @Cacheable(value = "seatsInfo",key = "'seatsinfo_'+#partId")
    public String getSeatsInfo(int partId){
        List<String> list = new ArrayList<>();
        list = seatsInterface.getUsedSeats(partId);
        return list2String(list);
    }

    private String list2String(List<String> list){
        StringBuffer string=new StringBuffer();
        for (String i:list){
            string.append(i);
            string.append(",");
        }
        String s = string.toString();
        if (s.length()!=0)
            s = s.substring(0,string.length()-1);
        return s;
    }

    /**通过测试
     * 预定座
     * @param row_col
     * @param partId
     * @param floorId
     * @return
     */
    @Caching(
        evict={
            @CacheEvict(key = "'seatsinfo_'+#p1",value = "seatsInfo"),
                @CacheEvict(key = "'parts_'+#p2",value = "parts")
        })
    public String reserveSeats(String row_col,int partId,int floorId,int stuId,String type){
        if (seatsInterface.getSeatsState(row_col,partId)==null){
           if(reserveSeats(row_col,floorId,stuId,type,partId)!="")
               return "错误";
        }else{
            return "已被占用";
        }
        return "成功";
    }
    @Transactional
    private String reserveSeats(String row_col,int floorId,int stuId,String type,int partId){
        int seatId = seatsInterface.getSeatsId(row_col,partId);
        Yuyue yuyue = new Yuyue();
        yuyue.setSeatId(seatId);
        yuyue.setStuId(stuId);
        if (type.equals("graduate")) {
            SeatStrategy seatStrategy = strategyService.getStratey(floorId,"graduate");
            yuyue.setSeatStrategies(new GraduateStrategy(floorId,seatStrategy));
        }
        else if (type.equals("undergraduate")) {
            SeatStrategy seatStrategy = strategyService.getStratey(floorId,"undergraduate");
            yuyue.setSeatStrategies(new UnderGraduateStrategy(floorId,seatStrategy));
        }
        else return "错误";
        yuyue.getReserve();
        seatsInterface.saveYuyue(yuyue);
        int orderId = seatsInterface.getYuyueByStuId(stuId).getOrderId();
        seatsInterface.seatAddOrder(seatId,orderId);
        return "";
    }

    /**测试通过
     * 取消预定
     * @param stuId
     */
    @Transactional
    @Caching(
            evict={
                    @CacheEvict(key = "'seatsinfo_'+#p1",value = "seatsInfo"),
                    @CacheEvict(key = "'parts_'+#p2",value = "parts")
            })
    public String cancelReservation(int stuId,int partId,int floorId){
        Yuyue yuyue = seatsInterface.getYuyueByStuId(stuId);
        String msg = yuyue.releaseSeats();
        if(msg.equals("失败"))
            return msg;
        seatsInterface.removeSeatOrder(yuyue.getSeatId());
        seatsInterface.updateYuyue(yuyue);
        return msg;
    }

    /**测试通过
     * 入座
     * @param cardNo
     * @return
     */
    @Transactional
    public String getSeat(String cardNo){
        Yuyue yuyue = seatsInterface.getYuyueByCardNo(cardNo);
        String type = borrowerJPA.findByCardNo(cardNo).getType();
        Map<String,String> map = new HashMap<>();
        if (yuyue==null){
            map.put("msg","没有预定");
        }else {
            int partId =seatsInterface.getPartIdBySeatId(yuyue.getSeatId());
            int floorId = seatsInterface.getFloorIdByPartId(partId);
            if (type.equals("graduate")) {
                SeatStrategy seatStrategy = strategyService.getStratey(floorId,"graduate");
                yuyue.setSeatStrategies(new GraduateStrategy(floorId,seatStrategy));
            }
            else if (type.equals("undergraduate")) {
                SeatStrategy seatStrategy = strategyService.getStratey(floorId,"undergraduate");
                yuyue.setSeatStrategies(new UnderGraduateStrategy(floorId,seatStrategy));
            }
            String msg = yuyue.getSeats();
            if (msg!="入座失败")
                seatsInterface.updateYuyue(yuyue);
            map.put("msg",msg);
        }
        return JSONObject.toJSONString(map);
    }

    /**测试通过
     * 学生端退座
     * @param stuId
     * @param partId
     * @param floorId
     * @return
     */
    @Transactional
    @Caching(
            evict={
                    @CacheEvict(key = "'seatsinfo_'+#p1",value = "seatsInfo"),
                    @CacheEvict(key = "'parts_'+#p2",value = "parts")
            })
    public String realseSeat(int stuId,int partId,int floorId){
        Yuyue yuyue = seatsInterface.getYuyueByStuId(stuId);
        Map<String,String> map = new HashMap<>();
        if(yuyue==null)
            map.put("msg","未查询到座位信息");
        else {
            String msg = yuyue.releaseSeats();
            if(msg!="失败") {
                seatsInterface.removeSeatOrder(yuyue.getSeatId());
                seatsInterface.updateYuyue(yuyue);
            }
            map.put("msp",msg);
        }
        return JSONObject.toJSONString(map);
    }

    /**测试通过
     * 管理员端退座
     * @param cardNo
     * @return
     */
    public String realseSeat(String cardNo){
        Map<String,String> map = new HashMap<>();
        Yuyue yuyue = seatsInterface.getYuyueByCardNo(cardNo);
        if(yuyue==null){
            map.put("msg","未查询到座位信息");
            return JSONObject.toJSONString(map);
        }
        int partId =seatsInterface.getPartIdBySeatId(yuyue.getSeatId());
        int floorId = seatsInterface.getFloorIdByPartId(partId);
        return realseSeat(yuyue.getStuId(),partId,floorId);
    }

    /**测试通过
     * 刷卡续座
     * @param cardNo
     * @return
     */
    @Transactional
    public String continueSeat(String cardNo){
        Yuyue yuyue = seatsInterface.getYuyueByCardNo(cardNo);
        String type = borrowerJPA.findByCardNo(cardNo).getType();
        Map<String,String> map = new HashMap<>();
        if (yuyue==null){
            map.put("msg","没有信息");
        }else {

            int partId =seatsInterface.getPartIdBySeatId(yuyue.getSeatId());
            int floorId = seatsInterface.getFloorIdByPartId(partId);
            if (type.equals("graduate")) {
                SeatStrategy seatStrategy = strategyService.getStratey(floorId,"graduate");
                yuyue.setSeatStrategies(new GraduateStrategy(floorId,seatStrategy));
            }
            else if (type.equals("undergraduate")) {
                SeatStrategy seatStrategy = strategyService.getStratey(floorId,"undergraduate");
                yuyue.setSeatStrategies(new UnderGraduateStrategy(floorId,seatStrategy));
            }
            String msg = yuyue.continueSeats();
            if (msg=="续座成功")
                seatsInterface.updateYuyue(yuyue);
            map.put("msg",msg);
        }
        return JSONObject.toJSONString(map);
    }

    /**Sat Dec 09 19:56:54 CST 201
     * 传出的时间是这种格式
     * 获取预约信息
     * @param stuId
     * @return
     */
    public Yuyue getYuyueByStuId(int stuId){
        return seatsInterface.getYuyueByStuId(stuId);
    }

}
