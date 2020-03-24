package com.example.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义消息发送通道
 */
public interface MySource02 {

    String MY_OUTPUT = "default.message";

    @Output(MY_OUTPUT)
    MessageChannel myOutput();

}