package com.course.seats.dao;

import com.course.seats.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import java.util.List;

/**
 * Created by 84074 on 2017/12/6.
 */
public interface SeatsInterface {
        int getFloorSNum();
        List<Floor> getFloors();
        int getSeatedNum(@Param("floorId") int floorId);

        List<Seatpart> getParts(@Param("floorId") int floorId);
        int getPartSeatedNum(@Param("partID") int partId);

        List<String> getUsedSeats(@Param("partId") int partId);
        Integer getSeatsState(@Param("row_col") String row_col);
        Integer getSeatsId(@Param("row_col") String row_col);

        SeatStrategy getStrategy(@Param("type") String type, @Param("floorId") int floorId);

        void saveYuyue(Yuyue yuyue);

        Yuyue getYuyueByStuId(int StuId);
        void updateYuyue(Yuyue yuyue);
}
