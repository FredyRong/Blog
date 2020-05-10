package top.fredyblog.blog.model.pojo;

import lombok.Data;

/**
 * 邮件消息类
 * @author Fredy
 * @date 2020/5/10 14:55
 */
@Data
public class MailMessageInfo {
    //接收方邮件
    private String email;
    //主题
    private String subject;
    //邮件内容
    private String content;
    //模块
    private String template;
}
