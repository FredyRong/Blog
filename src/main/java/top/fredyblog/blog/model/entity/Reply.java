package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Reply implements Serializable {
    //主键
    private Integer replyId;
    //评论者id
    private Integer commentId;
    //回复者id
    private Integer userId;
    //回复时间
    private LocalDateTime replyTime;
    //被回复者id
    private Integer repliedUserId;
    //回复内容
    private String replyContent;
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