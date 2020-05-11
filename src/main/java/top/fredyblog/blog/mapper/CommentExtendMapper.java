package top.fredyblog.blog.mapper;

import org.springframework.data.repository.query.Param;
import top.fredyblog.blog.model.pojo.CommentExtend;

import java.util.List;

/**
 * 评论管理拓展mapper
 * @author Fredy
 * @date 2020/5/11 18:40
 */
public interface CommentExtendMapper {

    /**
     * 功能描述：获取评论信息列表
     * @param blogId
     * @return
     */
    List<CommentExtend> getComments(@Param("blogId") Integer blogId);
}
