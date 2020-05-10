package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.entity.Message;
import top.fredyblog.blog.model.entity.MessageExample;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.MessageExtend;

/**
 * 留言服务层接口
 * @author Fredy
 * @date 2020/5/10 11:44
 */
public interface MessageService {
    /**
     * 功能描述：分页获取留言信箱列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize);

    /**
     * 功能描述：新增留言
     * @param nickname
     * @param email
     * @param content
     * @param user
     */
    void addMessage(String nickname, String email, String content, User user);

    /**
     * 功能描述：新增留言回复
     * @param nickname
     * @param email
     * @param content
     * @param messageId
     * @param repliedUserId
     * @param repliedUserNickname
     * @param user
     */
    void addMessageReply(String nickname, String email, String content, Integer messageId, Integer repliedUserId, String repliedUserNickname, User user);

    /**
     * 功能描述：分页查询留言信息（不考虑分层）
     * @param messageExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Message> page(MessageExample messageExample, Integer pageNum, Integer pageSize);
}
