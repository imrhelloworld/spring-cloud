package com.example.consumer;

/**
 * 消息消费者
 */
// @Component
// @EnableBinding(MySink02.class)
public class MyMessageConsumer02 {

    /**
     * 接收消息
     *
     * @param message
     */
    // @StreamListener(MySink02.MY_INPUT)
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
