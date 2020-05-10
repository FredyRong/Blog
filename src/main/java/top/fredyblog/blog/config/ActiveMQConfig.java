package top.fredyblog.blog.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * ActiveMQ配置类
 * @author Fredy
 * @date 2020/5/10 14:38
 */
@Component
@EnableJms
public class ActiveMQConfig {
    //留言队列名
    @Value("${queueName.message}")
    private String messageQueueName;

    //留言回复队列名
    @Value("${queueName.message.reply}")
    private String messageReplyQueueName;

    //评论队列名
    @Value("${queueName.comment}")
    private String commentQueueName;

    @Value("${queueName.reply}")
    private String replyQueueName;

    //注入留言队列实例
    @Bean
    public Queue messageQueue(){
        return new ActiveMQQueue(messageQueueName);
    }

    //注入留言回复队列实例
    @Bean
    public Queue messageReplyQueue(){
        return new ActiveMQQueue(messageReplyQueueName);
    }

    //注入评论队列实例
    @Bean
    public Queue commentQueue(){
        return new ActiveMQQueue(commentQueueName);
    }

    //注入回复队列实例
    @Bean
    public Queue replyQueue(){
        return new ActiveMQQueue(replyQueueName);
    }
}
