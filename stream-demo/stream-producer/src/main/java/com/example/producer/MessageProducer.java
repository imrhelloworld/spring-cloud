package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 */
@Component
@EnableBinding(Source.class)
public class MessageProducer {

    @Autowired
    private Source source;

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
    }

}
