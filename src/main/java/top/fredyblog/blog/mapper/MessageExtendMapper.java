package top.fredyblog.blog.mapper;

import top.fredyblog.blog.model.entity.MessageExample;
import top.fredyblog.blog.model.pojo.MessageExtend;

import java.util.List;

/**
 * 留言拓展mapper
 * @author Fredy
 * @date 2020/5/10 11:52
 */
public interface MessageExtendMapper {
    /**
     * 功能描述：分页获取留言信箱列表
     * @param messageExample
     * @return
     */
    List<MessageExtend> page(MessageExample messageExample);
}
