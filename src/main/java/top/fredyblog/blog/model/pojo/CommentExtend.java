package top.fredyblog.blog.model.pojo;

import lombok.Data;
import top.fredyblog.blog.model.entity.Comment;

import java.util.List;

/**
 * 评论拓展类
 * @author Fredy
 * @date 2020/5/11 18:34
 */
@Data
public class CommentExtend extends Comment {
    //评论用户昵称
    private String nickname;
    //评论用户头像
    private String headPortrait;
    //评论的回复数
    private long replyNums;
    //回复列表
    private List<ReplyExtend> replyList;
}
