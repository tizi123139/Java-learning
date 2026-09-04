package org.example.consultant.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.consultant.pojo.Reservation;

@Mapper
public interface ReservationMapper {

    @Insert("insert into reservation(name,gender,phone,communication_time,province,estimated_score) values(#{name},#{gender},#{phone},#{communicationTime},#{province},#{estimatedScore})")
    //添加预约信息
    void insert(Reservation reservation);

    @Select("select * from reservation where phone=#{phone}")
    Reservation findByPhone(String phone);
}
