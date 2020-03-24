package com.example.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
@EnableBinding(Sink.class)
public class MessageConsumer {

    /**
     * 接收消息
     *
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
