package com.example.consumer;

/**
 * 消息消费者
 */
// @Component
// @EnableBinding(MySink.class)
public class MyMessageConsumer {

    /**
     * 接收消息
     *
     * @param message
     */
    // @StreamListener(MySink.MY_INPUT)
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
