package top.fredyblog.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.fredyblog.blog.model.pojo.ReplyExtend;

import java.util.List;


/**
 * 回复管理拓展mapper
 * @author Fredy
 * @date 2020/5/10 11:53
 */
public interface ReplyExtendMapper {
    /**
     * 功能描述：获取回复列表
     * @param commentId
     * @return
     */
    List<ReplyExtend> getReplys(@Param("commentId") Integer commentId);
}
