package top.fredyblog.blog.service;

import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.ReplyExtend;

import java.util.List;

/**
 * 回复服务接口
 * @author Fredy
 * @date 2020/5/11 18:45
 */
public interface ReplyService {
    /**
     * 功能描述：查询某评论下的回复
     * @param commentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ReplyExtend> getReplyList(Integer commentId, Integer pageNum, Integer pageSize);

    /**
     * 功能描述：新增回复信息
     * @param commentId
     * @param blogId
     * @param content
     * @param user
     * @param repliedUserId
     * @param replyType
     */
    void addReplys(Integer commentId, Integer blogId, String content, User user, Integer repliedUserId, String replyType);
}
