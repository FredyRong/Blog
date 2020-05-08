package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.entity.Tag;
import top.fredyblog.blog.model.entity.TagExample;

import java.util.List;
import java.util.Map;

/**
 * 标签服务层接口
 * @author Fredy
 * @date 2020/5/7 18:19
 */
public interface TagService {
    /**
     * 功能描述: 查询所有标签（map形式）
     * @return
     */
    Map<Integer, String> getAllTags();

    /**
     * 功能描述: 查询所有标签列表（list形式）
     * @return
     */
    List<Tag> getAll();

    /**
     * 功能描述: 分页查询标签信息
     * @param tagExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Tag> page(TagExample tagExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 主键查询
     * @param id
     * @return
     */
    Tag findOne(Integer id);

    /**
     * 功能描述: 根据名称查询标签信息
     * @param tagName
     * @return
     */
    Tag findTagByName(String tagName);

    /**
     * 功能描述: 保存标签信息
     * @param tag
     */
    void saveTag(Tag tag);

    /**
     * 功能描述: 删除标签信息
     * @param id
     */
    void deleteTag(Integer id);
}
