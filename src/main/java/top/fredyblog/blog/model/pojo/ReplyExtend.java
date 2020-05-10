package top.fredyblog.blog.model.pojo;

import lombok.Data;
import top.fredyblog.blog.model.entity.Reply;

/**
 * 回复拓展类
 * @author Fredy
 * @date 2020/5/10 11:55
 */
@Data
public class ReplyExtend extends Reply {
    //回复用户昵称
    private String nickname;
    //回复用户头像
    private String headPortrait;
    //被回复用户昵称
    private String repliedNickName;
    //被回复用户头像
    private String repliedHeadPortrait;
}
