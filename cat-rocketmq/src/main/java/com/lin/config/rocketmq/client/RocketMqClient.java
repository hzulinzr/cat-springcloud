package com.lin.config.rocketmq.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * RocketMQ客户端
 * @author hzh
 */
@Slf4j
public class RocketMqClient {

	private DefaultMQProducer defaultMQProducer;

	public RocketMqClient(DefaultMQProducer defaultMQProducer) {
		this.defaultMQProducer = defaultMQProducer;
	}

	/**
	 * 发送消息
	 * @param message 消息内容
	 * @return 返回发送结果
	 */
	public SendResult sendMessage(Message message) {
		try {
			return defaultMQProducer.send(message);
		} catch (MQClientException | RemotingException | MQBrokerException e) {
			log.error("【cat RocketMqStarter】消息发送异常: " + e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error("【cat RocketMqStarter】消息发送线程终端异常: " + e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		return null;
	}
}
