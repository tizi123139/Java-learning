package com.itheima.consumer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String message){
        log.info("Received Message:{}",message);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue(String message) throws InterruptedException {
        log.info("Received Message:{}",message);
    }

}
