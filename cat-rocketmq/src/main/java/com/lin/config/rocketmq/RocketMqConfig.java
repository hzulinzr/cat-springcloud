package com.lin.config.rocketmq;

import com.lin.config.rocketmq.client.RocketMqClient;
import com.lin.config.rocketmq.handler.RocketMqMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;

/**
 * @author lzr
 * rocketmq配置类
 * @date 2020-09-14 11:53:36
 */
@Slf4j
public class RocketMqConfig {
    private String nameSrvAddr = "127.0.0.1:9876";

    private String producerGroup = "cat-producer";

    /**
     * 创建生产者实例
     * @return 返回生产者实例
     * @throws MQClientException mq客户端异常
     */
    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        log.info("cat 初始化rocketMq生产者, nameSrv: {}", nameSrvAddr);
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        defaultMQProducer.setVipChannelEnabled(false);
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(10);
        defaultMQProducer.start();
        return defaultMQProducer;
    }

    /**
     * 创建消费者实例
     * @param rocketMqMessageHandler
     * @return
     */
    @Bean
    public RocketMqListener rocketMqListener(RocketMqMessageHandler rocketMqMessageHandler) {
        log.info("【cat RocketMqStarter】初始化RocketMq消费者");
        return new RocketMqListener(rocketMqMessageHandler);
    }

    /**
     * 初始化rocketMq客户端
     * @param defaultMQProducer
     * @return
     */
    @Bean
    public RocketMqClient rocketMqClient(DefaultMQProducer defaultMQProducer){
        log.info("初始化rocketMq客户端, nameSrv: {} ", nameSrvAddr);
        return new RocketMqClient(defaultMQProducer);
    }

}
