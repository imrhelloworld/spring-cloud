package com.example.consumer;

import com.example.channel.MyProcessor;
import com.example.producer.SmsAndEmailMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
@EnableBinding(MyProcessor.class)
public class SourceMessageConsumer {

    private Logger logger = LoggerFactory.getLogger(SourceMessageConsumer.class);

    @Autowired
    private SmsAndEmailMessageProducer smsAndEmailMessageProducer;

    /**
     * 接收原始消息，处理后并发送
     *
     * @param sourceMessage
     */
    @StreamListener(MyProcessor.SOURCE_MESSAGE)
    public void receive(String sourceMessage) {
        logger.info("原始消息接收成功，原始消息为：{}", sourceMessage);
        // 发送消息 电话号码
        smsAndEmailMessageProducer.sendSms(sourceMessage.split("\\|")[0]);
        // 发送消息 邮箱地址
        smsAndEmailMessageProducer.sendEmail(sourceMessage.split("\\|")[1]);
    }

}
