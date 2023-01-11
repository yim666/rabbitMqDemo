package com.example.producer;

import com.example.producer.config.RabbitMqConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProducerApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        //生产者发送消息
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.EXCHANGE_NAME,"boot.h","boot mq hello~~~");
    }
    @Test
    void contextLoads() {
    }

}
