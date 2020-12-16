package com.lin.config.rocketmq.handler;

import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * RocketMQ消息处理器
 * @author hzh
 */
public interface RocketMqMessageHandler {

	/**
	 * 消息处理逻辑
	 * @param messageExtList 消息列表
	 */
	void messageHandler(List<MessageExt> messageExtList);
}
