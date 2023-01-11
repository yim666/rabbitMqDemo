package com.example.consumer.Listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListen {

    @RabbitListener(queues = "boot_queue")
    public  void listener1(String message){
        System.out.println("消费者接收到消息："+message);
    }
}
