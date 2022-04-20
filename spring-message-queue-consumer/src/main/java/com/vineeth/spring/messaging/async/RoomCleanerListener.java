package com.vineeth.spring.messaging.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomCleanerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCleanerListener.class);

    @Autowired
    private ObjectMapper objectMapper;


    public void receiveMessage(String message) {
        try {
            AsyncPayload asyncPayload = objectMapper.readValue(message, AsyncPayload.class);
            LOGGER.info("Model: "+asyncPayload.getModel());
            LOGGER.info("Id: "+asyncPayload.getId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
