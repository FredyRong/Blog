package top.fredyblog.blog.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import top.fredyblog.blog.model.mq.MsgProducer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * 消息队列工具类
 * @author Fredy
 * @date 2020/5/10 14:19
 */
@Log4j2
@Component
public class MqUtil {
    @Resource
    private MsgProducer msgProducer;

    @Resource
    @Qualifier("messageQueue")
    private Queue messageQueue;

    @Resource
    @Qualifier("messageReplyQueue")
    private Queue messageReplyQueue;

    @Resource
    @Qualifier("commentQueue")
    private Queue commentQueue;

    @Resource
    @Qualifier("replyQueue")
    private Queue replyQueue;

    public void sendMessageToMq(Object msg){
        try {
            msgProducer.prodeceMsg(messageQueue,msg);
        } catch (JMSException e) {
            log.error("发送留言消息[{}]异常！",msg,e);
        }
    }

    public void sendMessageReplyToMq(Object msg){
        try {
            msgProducer.prodeceMsg(messageReplyQueue,msg);
        } catch (JMSException e) {
            log.error("发送留言回复消息[{}]异常！",msg,e);
        }
    }

    public void sendCommentToMq(String msg){
        try {
            msgProducer.prodeceMsg(commentQueue,msg);
        } catch (JMSException e) {
            log.error("发送评论消息[{}]异常！",msg,e);
        }
    }

    public void sendReplyToMq(String msg){
        try {
            msgProducer.prodeceMsg(replyQueue,msg);
        } catch (JMSException e) {
            log.error("发送回复消息[{}]异常！",msg,e);
        }
    }
}
