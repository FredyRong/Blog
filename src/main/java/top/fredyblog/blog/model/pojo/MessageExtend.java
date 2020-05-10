package top.fredyblog.blog.model.pojo;

import lombok.Data;
import top.fredyblog.blog.model.entity.Message;

import java.util.List;

/**
 * 留言拓展类
 * @author Fredy
 * @date 2020/5/10 11:47
 */
@Data
public class MessageExtend extends Message {
    //留言的回复数
    private long replyNums;
    //回复列表
    private List<Message> replyList;
}
