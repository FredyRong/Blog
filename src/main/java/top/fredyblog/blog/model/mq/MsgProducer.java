package top.fredyblog.blog.model.mq;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
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
//    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    @Bean(value="jmsMessagingTemplate")
    JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
        return messagingTemplate;
    }

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
