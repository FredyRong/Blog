package top.fredyblog.blog.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import top.fredyblog.blog.model.pojo.MailMessageInfo;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * 邮件工具类
 * @author Fredy
 * @date 2020/5/10 14:45
 */
@Component
@Log4j2
public class MailUtil {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(MailMessageInfo mailMessageInfo) throws MessagingException {
        //邮件接收方
        String email = mailMessageInfo.getEmail();
        //邮件内容
        String content = mailMessageInfo.getContent();
        //邮件主题
        String subject = mailMessageInfo.getSubject();
        //组装参数
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);
        //发送邮件
        log.info("发送邮件信息到邮箱[{}]>>>>>>start", email);
        long start = System.currentTimeMillis();
        javaMailSender.send(message);
        long end = System.currentTimeMillis();
        log.info("发送邮件信息到邮箱[{}]>>>>>>end，耗时{}s", email, ((float) (end - start)) / 1000);
    }
}
