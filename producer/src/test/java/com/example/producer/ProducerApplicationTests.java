package com.example.producer;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.producer.config.RabbitMqConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProducerApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void testSend() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aa");
        strings.add("bb");
        strings.add("cc");
        //生产者发送消息
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.EXCHANGE_NAME, "boot.h", strings);
        rabbitTemplate.convertAndSend("boot_topic_exchange", "boot.h", "boot topic hello~~~");
    }

    @Test
    void createExchange() {
        //createExchange
        Exchange adminExchange = ExchangeBuilder.directExchange("adminExchange").durable(true).build();
        System.out.println(adminExchange);
        rabbitAdmin.declareExchange(adminExchange);
    }

    @Test
    void createQueue() {
        //* The queue is durable, non-exclusive and non auto-delete.
        rabbitAdmin.declareQueue(new Queue("amqpQueue21"));
        System.out.println("success");
    }

    @Test
    void createBinding(){
        rabbitAdmin.declareBinding(new Binding("amqpQueue21", Binding.DestinationType.QUEUE,"adminExchange","amqp.#",null));
        System.out.println("success");

    }
}
