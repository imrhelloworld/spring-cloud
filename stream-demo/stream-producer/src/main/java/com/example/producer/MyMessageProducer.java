package com.example.producer;

import com.example.channel.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息生产者
 */
// @Component
// @EnableBinding(MySource.class)
public class MyMessageProducer {

    @Autowired
    private MySource mySource;

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        mySource.myOutput().send(MessageBuilder.withPayload(message).build());
    }

}
