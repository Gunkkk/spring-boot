package com.course.seats.dao;

import com.course.seats.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import java.util.List;

/**
 * Created by 84074 on 2017/12/6.
 */
public interface SeatsInterface {
        Integer getFloorSNum();
        List<Floor> getFloors();
        Integer getSeatedNum(@Param("floorId") int floorId);

        List<Seatpart> getParts(@Param("floorId") int floorId);
        Integer getPartSeatedNum(@Param("partId") int partId);

        List<String> getUsedSeats(@Param("partId") int partId);
        Integer getSeatsState(@Param("row_col") String row_col);
        Integer getSeatsId(@Param("row_col") String row_col);

        SeatStrategy getStrategy(@Param("type") String type, @Param("floorId") int floorId);

        void saveYuyue(@Param("yuyue") Yuyue yuyue);
        void seatAddOrder(@Param("seatId") int seatId,@Param("orderId") int orderId);
        void removeSeatOrder(@Param("seatId") int seatId);

        Yuyue getYuyueByStuId(@Param("stuId") int StuId);
        void updateYuyue(@Param("yuyue") Yuyue yuyue);

        Integer getPartIdBySeatId(@Param("seatId") int seatId);
        Integer getFloorIdByPartId(@Param("partId") int partId);
        Yuyue getYuyueByCardNo(@Param("cardNo") String cardNo);
}
