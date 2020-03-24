package com.example.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息通道
 */
public interface MyProcessor {

    String SOURCE_MESSAGE = "source.message";
    String SMS_MESSAGE = "sms.message";
    String EMAIL_MESSAGE = "email.message";

    @Output(SOURCE_MESSAGE)
    MessageChannel sourceOutput();

    @Input(SMS_MESSAGE)
    SubscribableChannel smsInput();

    @Input(EMAIL_MESSAGE)
    SubscribableChannel emailInput();

}
