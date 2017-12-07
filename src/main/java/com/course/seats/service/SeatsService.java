package com.course.seats.service;

import com.course.seats.dao.SeatsInterface;
import com.course.seats.entity.Floor;
import com.course.seats.entity.Seatpart;
import com.course.seats.entity.Yuyue;
import com.course.seats.strategy.GraduateStrategy;
import com.course.seats.strategy.UnderGraduateStrategy;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 84074 on 2017/12/6.
 */
@Service
@CacheConfig(cacheNames = "seats")
public class SeatsService {
    @Autowired
    SeatsInterface seatsInterface;

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
    @Cacheable(key = "partsinfo_+#p0")
    public String getPartsInfo(int floorId){
        List<Map<String,Integer>> partInfo = new ArrayList<>();
        int seatNum,seatedNum;
        List<Seatpart> partList = seatsInterface.getParts(floorId);
        for(Seatpart i:partList){
            Map<String,Integer> map = new HashMap<>();
            seatNum = i.getSeatSum();
            seatedNum = seatsInterface.getPartSeatedNum(i.getPartId());
            map.put("seatNum",seatNum);
            map.put("seatedNum",seatedNum);
            partInfo.add(map);
        }
        return JSONArray.toJSONString(partInfo);
    }

    /**
     * 返回一个区域的作为被占用情况
     * @param partId
     * @return
     */
    @Cacheable(key = "seatsinfo_+#p0")
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
        return string.toString();
    }

    /**
     * 预定座
     * @param row_col
     * @param partId
     * @param floorId
     * @return
     */
    @Caching(
        evict={
            @CacheEvict(key = "seatsinfo_+#p1"),
                @CacheEvict(key = "partsinfo_+#p2")
        })
    public String reserveSeats(String row_col,int partId,int floorId,int stuId,String type){
        if (seatsInterface.getSeatsState(row_col)!=null){
           reserveSeats(row_col,floorId,stuId,type);
        }else{
            return "已被占用";
        }
        return "成功";
    }
    @Transactional
    private void reserveSeats(String row_col,int floorId,int stuId,String type){
        int seatId = seatsInterface.getSeatsId(row_col);
        Yuyue yuyue = new Yuyue();
        yuyue.setSeatId(seatId);
        yuyue.setStuId(stuId);
        if (type=="graduate")
            yuyue.setSeatStrategies(new GraduateStrategy(floorId));
        else if (type=="undergraduate")
            yuyue.setSeatStrategies(new UnderGraduateStrategy(floorId));
        yuyue.getReserve();
        seatsInterface.saveYuyue(yuyue);
    }

    /**
     * 取消预定
     * @param stuId
     */
    @Transactional
    @Caching(
            evict={
                    @CacheEvict(key = "seatsinfo_+#p1"),
                    @CacheEvict(key = "partsinfo_+#p2")
            })
    public void cancelReservation(int stuId,int partId,int floorId){
        Yuyue yuyue = seatsInterface.getYuyueByStuId(stuId);
        yuyue.releaseSeats();
        seatsInterface.updateYuyue(yuyue);
    }
}
