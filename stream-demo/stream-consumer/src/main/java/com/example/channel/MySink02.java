package com.example.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息接收通道
 */
public interface MySink02 {

    String MY_INPUT = "default.message";

    @Input(MY_INPUT)
    SubscribableChannel myInput();

}