package com.example.consumer;

import com.example.channel.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
@EnableBinding(MyProcessor.class)
public class SmsAndEmailMessageConsumer {

    private Logger logger = LoggerFactory.getLogger(SmsAndEmailMessageConsumer.class);

    /**
     * 接收消息 电话号码
     *
     * @param phoneNum
     */
    @StreamListener(MyProcessor.SMS_MESSAGE)
    public void receiveSms(String phoneNum) {
        logger.info("电话号码为：{}，调用短信发送服务，发送短信...", phoneNum);
    }

    /**
     * 接收消息 邮箱地址
     *
     * @param emailAddress
     */
    @StreamListener(MyProcessor.EMAIL_MESSAGE)
    public void receiveEmail(String emailAddress) {
        logger.info("邮箱地址为：{}，调用邮件发送服务，发送邮件...", emailAddress);
    }

}
