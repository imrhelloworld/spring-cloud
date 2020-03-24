package com.example.producer;

import com.example.channel.MySource02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息生产者
 */
// @Component
// @EnableBinding(MySource02.class)
public class MyMessageProducer02 {

    @Autowired
    private MySource02 mySource02;

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        mySource02.myOutput().send(MessageBuilder.withPayload(message).build());
    }

}
