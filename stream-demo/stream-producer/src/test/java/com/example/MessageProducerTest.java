package com.example;

import com.example.producer.MessageProducer;
import com.example.producer.SourceMessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {StreamProducerApplication.class})
public class MessageProducerTest {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void testSend() {
        for (int i = 1; i <= 10; i++) {
            messageProducer.send("hello spring cloud stream");
        }
    }

    // @Autowired
    // private MyMessageProducer myMessageProducer;

    // @Test
    // public void testMySend() {
    //     myMessageProducer.send("hello spring cloud stream");
    // }

    // @Autowired
    // private MyMessageProducer02 myMessageProducer02;

    // @Test
    // public void testMySend02() {
    //     myMessageProducer02.send("约定大于配置");
    // }

    @Autowired
    private SourceMessageProducer sourceMessageProducer;

    @Test
    public void testSendSource() {
        sourceMessageProducer.send("10086|10086@email.com");
    }

}
