package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Comment implements Serializable {
    //评论id
    private Integer commentId;
    //评论用户id
    private Integer userId;
    //评论博客id
    private Integer blogId;
    //评论内容
    private String commentContent;
    //评论时间
    private LocalDateTime commentTime;
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