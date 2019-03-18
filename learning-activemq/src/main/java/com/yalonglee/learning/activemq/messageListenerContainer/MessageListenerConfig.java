package com.yalonglee.learning.activemq.messageListenerContainer;

import com.yalonglee.learning.activemq.consumer.PushConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

@Slf4j
@Configuration
public class MessageListenerConfig {

    /**
     * 用户名
     */
    @Value("admin")
    private String username;

    /**
     * 密码
     */
    @Value("admin")
    private String password;

    /**
     * 连接地址
     */
    @Value("tcp://106.14.15.55:61616")
    private String brokerUrl;

    /**
     * 测试队列
     */
    @Value("test-queue-name")
    private String testQueueName;


    @Bean
    public ActiveMQQueue paymentQueue() {
        return new ActiveMQQueue(testQueueName);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        //重写传送策略
        RedeliveryPolicy policy = activeMQConnectionFactory.getRedeliveryPolicy();
        //初始重发延迟时间
        policy.setInitialRedeliveryDelay(5000);
        //重连时间间隔递增倍数
        policy.setBackOffMultiplier(2);
        //启用指数递增的方式增加延时时间
        policy.setUseExponentialBackOff(true);
        //最大传送次数
        policy.setMaximumRedeliveries(2);

        CachingConnectionFactory bean = new CachingConnectionFactory(activeMQConnectionFactory);
        bean.setSessionCacheSize(5);
        return bean;
    }

    @Bean
    @Autowired
    public DefaultMessageListenerContainer withdrawCashesPushConsumerMessageListenerContainer(PushConsumer pushConsumer
            , ConnectionFactory connectionFactory
            , ActiveMQQueue withdrawCashesQueue) {
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setMessageListener(pushConsumer);
        listenerContainer.setDestination(withdrawCashesQueue);
        listenerContainer.setConnectionFactory(connectionFactory);

        listenerContainer.setAcceptMessagesWhileStopping(false);
        listenerContainer.setSessionTransacted(false);
        listenerContainer.setConcurrentConsumers(2);
        listenerContainer.setMaxMessagesPerTask(6);
        listenerContainer.setReceiveTimeout(5000);
        listenerContainer.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        log.info("DefaultMessageListenerContainer for queue [{}] with message selector [{}] was started", listenerContainer.getDestination(), listenerContainer.getMessageSelector());
        return listenerContainer;
    }

    @Bean
    @Autowired
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

}