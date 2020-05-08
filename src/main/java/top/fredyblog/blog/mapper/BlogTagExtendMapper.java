package top.fredyblog.blog.mapper;

import top.fredyblog.blog.model.pojo.TopTag;

import java.util.List;

/**
 * @author Fredy
 * @date 2020/5/6 23:34
 */
public interface BlogTagExtendMapper {
    /**
     * 功能描述：查询指定数量的top标签博客数
     * @param topSize
     * @return
     */
    List<TopTag> getTopTagList(Integer topSize);
}
