import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-20 14:18:12
 */
@Service
public class RocketMqProvider {
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String produerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;
    /**
     * @Desc <p> 延迟消息发送</p>
     * @Param
     * @Return void
     */
    public void delayMQProducer() {

        DefaultMQProducer producer = new DefaultMQProducer(produerGroup);

        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();

            Message message = new Message("TopicTest", "push", "发送延迟消息---".getBytes());
            message.setDelayTimeLevel(3);

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            for (int i = 0; i < 1; i++) {
                SendResult result = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        int index = id % list.size();
                        return list.get(index);
                    }
                }, 1,3000);
            }
            stopWatch.stop();
            System.out.println(new Date().toString() + " 发送1条延迟消息耗时:" + stopWatch.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

}
