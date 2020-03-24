package com.example.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息接收通道
 */
public interface MySink {

    String MY_INPUT = "my_input";

    @Input(MY_INPUT)
    SubscribableChannel myInput();

}