package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.ReplyExtendMapper;
import top.fredyblog.blog.mapper.ReplyMapper;
import top.fredyblog.blog.model.entity.Reply;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.ReplyExtend;
import top.fredyblog.blog.service.ReplyService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Fredy
 * @date 2020/5/11 18:47
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Resource
    private ReplyExtendMapper replyExtendMapper;
    @Resource
    private ReplyMapper replyMapper;

    /**
     * 功能描述：查询某评论下的回复
     * @param commentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<ReplyExtend> getReplyList(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "reply_time");
        List<ReplyExtend> replyExtendList = replyExtendMapper.getReplys(commentId);
        return replyExtendList;
    }

    /**
     * 功能描述：新增回复信息
     * @param commentId
     * @param blogId
     * @param content
     * @param user
     * @param repliedUserId
     * @param replyType
     */
    @Override
    public void addReplys(Integer commentId, Integer blogId, String content, User user, Integer repliedUserId, String replyType) {
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setReplyContent(content);
        reply.setUserId(user.getUserId());
        LocalDateTime now = LocalDateTime.now();
        reply.setRepliedUserId(repliedUserId);
        reply.setReplyTime(now);
        reply.setCreateTime(now);
        reply.setUpdateTime(now);
        reply.setDelFlag(false);
        int n = replyMapper.insertSelective(reply);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.REPLY_INSERT_FAILED);
        }
    }
}
