package com.vineeth.spring.messaging.config;

import com.vineeth.spring.messaging.async.RoomCleanerListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncConfig {

    private static final String QUEUE_NAME = "room-cleaner";
    private static final String EXCHANGE_NAME = "operations";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("landon.rooms.cleaner");
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RoomCleanerListener roomCleanerListener){
        return new MessageListenerAdapter(roomCleanerListener, "receiveMessage");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }
}
