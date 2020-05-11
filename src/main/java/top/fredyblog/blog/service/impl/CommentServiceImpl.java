package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fredyblog.blog.constant.RedisConstant;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.CommentExtendMapper;
import top.fredyblog.blog.mapper.CommentMapper;
import top.fredyblog.blog.mapper.ReplyExtendMapper;
import top.fredyblog.blog.model.entity.Comment;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.CommentExtend;
import top.fredyblog.blog.model.pojo.ReplyExtend;
import top.fredyblog.blog.service.CommentService;
import top.fredyblog.blog.utils.RedisValueUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论管理服务层实现
 * @author Fredy
 * @date 2020/5/11 18:38
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentExtendMapper commentExtendMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private ReplyExtendMapper replyExtendMapper;
    @Resource
    private RedisValueUtil redisValueUtil;
    /**
     * 功能描述：获取评论信息列表
     * @param blogId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "comment_time desc");
        List<CommentExtend> commentExtends = commentExtendMapper.getComments(blogId);
        for (CommentExtend commentExtend : commentExtends) {
            List<ReplyExtend> replyExtendList = replyExtendMapper.getReplys(commentExtend.getCommentId());
            commentExtend.setReplyNums(replyExtendList.size());
            commentExtend.setReplyList(replyExtendList);
        }
        return commentExtends;
    }

    /**
     * 功能描述：新增评论信息
     * @param blogId
     * @param content
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComments(Integer blogId, String content, User user) {
        //新增评论信息
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setUserId(user.getUserId());
        comment.setBlogId(blogId);
        LocalDateTime now = LocalDateTime.now();
        comment.setCommentTime(now);
        comment.setCreateTime(now);
        comment.setDelFlag(false);
        comment.setUpdateTime(now);
        int n = commentMapper.insertSelective(comment);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.COMMENT_INSERT_FAILED);
        }
        //更新redis中博客评论数
        redisValueUtil.hIncr(RedisConstant.COMMENT_KEY.getDesc(), blogId.toString());
    }
}
