package com.yalonglee.learning.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class PushConsumer implements SessionAwareMessageListener<Message> {

    @Override
    public void onMessage(Message message, Session session) {
        if (message != null) {
            TextMessage textMessage = (TextMessage) message;
            try {
                double result = 0.5D;
                if (Math.random() > result) {
                    log.info("当前时间：{}，随机抛出异常：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), textMessage.getText());
                    throw new RuntimeException("随机抛出异常！");
                }
                log.info("当前时间：{}，消费消息：{}，消息已被签收！", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), textMessage.getText());
                message.acknowledge();
            } catch (Exception e) {
                try {
                    session.recover();
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
