package top.fredyblog.blog.model.mq;

import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * 消息发送器
 * @author Fredy
 * @date 2020/5/10 15:23
 */
@Component
@Log4j2
public class MsgProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 功能描述：生产消息
     * @param queue
     * @param message
     * @throws JMSException
     */
    public void prodeceMsg(Queue queue, Object message) throws JMSException {
        String queueName = queue.getQueueName();
        log.info("发送消息[{}]到队列[{}]中>>>>>>start", message, queueName);
        long start = System.currentTimeMillis();
        jmsMessagingTemplate.convertAndSend(queue, message);
        long end = System.currentTimeMillis();
        log.info("发送消息[{}]到队列[{}]中>>>>>>end，耗时{}s", message, queueName,
                ((float) (end - start)) / 1000);
    }
}
