package top.fredyblog.blog.model.mq;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.MessageDTO;
import top.fredyblog.blog.model.pojo.MailMessageInfo;
import top.fredyblog.blog.utils.MailUtil;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import java.util.regex.Pattern;

/**
 * 消息接收器
 * @author Fredy
 * @date 2020/5/10 14:43
 */
@Component
@Log4j2
public class MsgConsumer {
    @Resource
    private MailUtil mailUtil;
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 功能描述：处理留言信息
     * @param objectMessage
     * @throws JMSException
     * @throws MessagingException
     */
    @JmsListener(destination = "${queueName.message}")
    public void receiveMessageMsg(ObjectMessage objectMessage) throws JMSException,
            MessagingException {
        MessageDTO message = (MessageDTO) objectMessage.getObject();
        log.info("接收到留言消息：{}", message.toString());
        String name = message.getNickname();
        String content = message.getContent();
        MailMessageInfo mailMessageInfo = new MailMessageInfo();
        mailMessageInfo.setEmail(sender);
        mailMessageInfo.setSubject("留言新增通知");
        StringBuffer sb = new StringBuffer();
        sb.append("<h2>有个小伙伴给你留言啦！</h2>")
                .append("<hr>")
                .append("<p style='text-align:left'>留言者昵称：" + name + "</p>")
                .append("<p style='text-align:left'>留言内容：" + content + "</p>")
                .append("<hr>")
                .append("<p align='right' style='color: red;font-size:15px;'>此为系统邮件，请勿回复</p>")
                .append("<hr>")
                .append("<a href='http://xianzilei.cn/toMessagePage'>点此跳转博客留言板页面</a>");
        mailMessageInfo.setContent(sb.toString());
        mailUtil.sendMail(mailMessageInfo);
    }

    /**
     * 处理留言回复信息
     * @param objectMessage
     * @throws JMSException
     * @throws CustomizeException
     * @throws MessagingException
     */
    @JmsListener(destination = "${queueName.messageReply}")
    public void receiveMessageReplyMsg(ObjectMessage objectMessage) throws JMSException,
            CustomizeException,
            MessagingException {
        MessageDTO message = (MessageDTO) objectMessage.getObject();
        String name = message.getNickname();
        String content = message.getContent();
        String parentName = message.getParentNickName();
        String parentContent = message.getParentContent();
        String email = message.getEmail();
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
                "+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        if (!regex.matcher(email).matches()) {
            throw new CustomizeException(CustomizeErrorCode.MAIL_ILLEGAL);
        }
        MailMessageInfo mailMessageInfo = new MailMessageInfo();
        mailMessageInfo.setEmail(email);
        mailMessageInfo.setSubject("您收到一条回复（来自Blog）");
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>您好，" + parentName + "！有个小伙伴回复您啦！</h1>")
                .append("<hr>")
                .append("<h2>您的留言</h2>")
                .append("<p style='text-align:left'>留言内容：" + parentContent + "</p>")
                .append("<hr>")
                .append("<h2>您收到的回复</h2>")
                .append("<p style='text-align:left'>留言者昵称：" + name + "</p>")
                .append("<p style='text-align:left'>留言内容：" + content + "</p>")
                .append("<hr>")
                .append("<p align='right' style='color: red;font-size:15px;'>此为系统邮件，请勿回复</p>")
                .append("<hr>")
                .append("<a href='http://xianzilei.cn/toMessagePage'>点此跳转博客留言板页面</a>");
        mailMessageInfo.setContent(sb.toString());
        mailUtil.sendMail(mailMessageInfo);
    }
}
