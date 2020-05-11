package top.fredyblog.blog.service;

import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.CommentExtend;

import java.util.List;

/**
 * 评论管理服务层接口
 * @author Fredy
 * @date 2020/5/11 18:33
 */
public interface CommentService {
    /**
     * 功能描述：获取评论信息列表
     * @param blogId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize);

    /**
     * 功能描述：新增评论信息
     * @param blogId
     * @param content
     * @param user
     */
    void addComments(Integer blogId, String content, User user);

}
