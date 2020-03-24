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
public class SmsAndEmailMessageProducer {

    private Logger logger = LoggerFactory.getLogger(SmsAndEmailMessageProducer.class);

    @Autowired
    private MyProcessor myProcessor;

    /**
     * 发送消息 电话号码
     *
     * @param smsMessage
     */
    public void sendSms(String smsMessage) {
        logger.info("电话号码消息发送成功，消息为：{}", smsMessage);
        myProcessor.smsOutput().send(MessageBuilder.withPayload(smsMessage).build());
    }

    /**
     * 发送消息 邮箱地址
     *
     * @param emailMessage
     */
    public void sendEmail(String emailMessage) {
        logger.info("邮箱地址消息发送成功，消息为：{}", emailMessage);
        myProcessor.emailOutput().send(MessageBuilder.withPayload(emailMessage).build());
    }

}
