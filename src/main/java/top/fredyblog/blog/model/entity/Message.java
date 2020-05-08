package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Message implements Serializable {
    //主键
    private Integer messageId;
    //存储已经登录的用户id，否则为0
    private Integer messageUserId;
    //留言者昵称
    private String messageNickname;
    //被回复者id，一级留言为空，二级留言游客为0
    private Integer repliedUserId;
    //被回复者昵称，一级留言为空
    private String repliedUserNickname;
    //留言内容
    private String messageContent;
    //留言者邮箱
    private String messageEmail;
    //留言等级：1-留言，2-对留言的回复
    private String messageLevel;
    //父留言id，没有上层为0
    private Integer parentMessageId;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志：0-未删除 1-删除
    private Boolean delFlag;
    //删除时间
    private LocalDateTime delTime;

    private static final long serialVersionUID = 1L;
}