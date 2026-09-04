package org.example.consultant.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.example.consultant.pojo.Reservation;
import org.example.consultant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {
    @Autowired
    private ReservationService reservationService;

    @Tool("预约志愿填报")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号") String phone,
            @P("预约时间,格式: yyyy-MM-dd'T'HH:mm") String communicationTime,
            @P("所在省份")String province,
            @P("考生预约分数")Integer estimatedScore
    ){
        Reservation reservation = new Reservation(null,name,gender,phone, LocalDateTime.parse(communicationTime),province,estimatedScore);
        reservationService.insert(reservation);

    }

    @Tool("根据手机号查询预约单")
    public Reservation findReservation(@P("考生手机号")String phone){
        return reservationService.findByPhone(phone);
    }
}
