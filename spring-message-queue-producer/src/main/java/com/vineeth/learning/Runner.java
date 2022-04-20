package com.vineeth.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vineeth.learning.async.AsyncPayload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void run(String... args) throws Exception {
        int index =(int) ((Math.random()) * (28 -1))+1;
        AsyncPayload asyncPayload = new AsyncPayload();
        asyncPayload.setId(index);
        asyncPayload.setModel("Room "+index);
        rabbitTemplate.convertAndSend("operations", "landon.rooms.cleaner", objectMapper.writeValueAsString(asyncPayload));
        configurableApplicationContext.close();
    }
}
