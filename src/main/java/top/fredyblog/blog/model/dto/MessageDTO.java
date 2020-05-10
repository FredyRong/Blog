package top.fredyblog.blog.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息传输类
 * @author Fredy
 * @date 2020/5/10 14:15
 */
@Data
public class MessageDTO implements Serializable {
    //留言者昵称
    private String nickname;
    //留言内容
    private String content;
    //被留言者昵称
    private String parentNickName;
    //被留言内容
    private String parentContent;
    //发送的指定邮箱
    private String email;

    private static final long serialVersionUID = 1L;
}
