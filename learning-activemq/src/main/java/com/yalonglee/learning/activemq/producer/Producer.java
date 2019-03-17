package com.yalonglee.learning.activemq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String queueName, String message) {
        jmsTemplate.send(queueName, (Session session) -> {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            return textMessage;
        });
    }
}
