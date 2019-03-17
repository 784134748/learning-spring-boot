package com.yalonglee.learning.activemq;

import com.yalonglee.learning.activemq.producer.Producer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveMQTest extends LearningActivemqApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void testProducer() throws InterruptedException {
        int i = 0;
        int max = 20;
        while (i < max) {
            Thread.sleep(100);
            if (i == max - 1) {
                continue;
            }
            producer.send("test-queue-name", "消息" + i);
            i++;
        }
    }
}
