package com.yalonglee.learning.activemq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 消息的重复投递设置仅作用于消费者端（eg:listener、consumer）
     *
     * @param queueName
     * @param message
     */
    public void send(String queueName, String message) {
        jmsTemplate.send(queueName, (Session session) -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            //设置重复投递的时间间隔
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 20000L);
            //设置延时投递的时间
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 0L);
            //设置重复投递的次数
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 1);
            //设置消息的持久化
            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            return textMessage;
        });
    }
}
