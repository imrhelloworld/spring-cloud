package com.example.producer;

import com.example.channel.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 */
@Component
@EnableBinding(MyProcessor.class)
public class SourceMessageProducer {

    private Logger logger = LoggerFactory.getLogger(SourceMessageProducer.class);

    @Autowired
    private MyProcessor myProcessor;

    /**
     * 发送原始消息
     *
     * @param sourceMessage
     */
    public void send(String sourceMessage) {
        logger.info("原始消息发送成功，原始消息为：{}", sourceMessage);
        myProcessor.sourceOutput().send(MessageBuilder.withPayload(sourceMessage).build());
    }

}
