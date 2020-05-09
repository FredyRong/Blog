package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.TagMapper;
import top.fredyblog.blog.model.entity.Tag;
import top.fredyblog.blog.model.entity.TagExample;
import top.fredyblog.blog.service.TagService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fredy
 * @date 2020/5/7 18:21
 */
@Service
@Log4j2
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    /**
     * 功能描述: 查询所有标签（map形式）
     * @return
     */
    @Override
    public Map<Integer, String> getAllTags() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        return tags.stream().collect(Collectors.toMap(Tag::getTagId, Tag::getTagName, (k1, k2) -> k2));
    }

    /**
     * 功能描述: 查询所有标签列表（list形式）
     * @return
     */
    @Override
    public List<Tag> getAll() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        return tagMapper.selectByExample(tagExample);
    }

    /**
     * 功能描述: 分页查询标签信息
     * @param tagExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Tag> page(TagExample tagExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> list = tagMapper.selectByExample(tagExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 主键查询
     * @param id
     * @return
     */
    @Override
    public Tag findOne(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述: 根据名称查询标签信息
     * @param tagName
     * @return
     */
    @Override
    public Tag findTagByName(String tagName) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andTagNameEqualTo(tagName);
        List<Tag> list = tagMapper.selectByExample(tagExample);
        return CollectionUtils.isEmpty(list)? null : list.get(0);
    }

    /**
     * 功能描述: 保存标签信息
     * @param tag
     */
    @Override
    public void saveTag(Tag tag) {
        LocalDateTime now = LocalDateTime.now();
        tag.setUpdateTime(now);
        if (tag.getTagId() == null) {
            tag.setCreateTime(now);
            tag.setDelFlag(false);
            int n = tagMapper.insertSelective(tag);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.TAG_INSERT_FAILED);
            }
        } else {
            int n = tagMapper.updateByPrimaryKeySelective(tag);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.TAG_UPDATE_FAILED);
            }
        }
    }

    /**
     * 功能描述: 删除标签信息
     * @param id
     */
    @Override
    public void deleteTag(Integer id) {
        Tag tag = new Tag();
        tag.setTagId(id);
        tag.setDelFlag(true);
        tag.setDelTime(LocalDateTime.now());
        int n = tagMapper.updateByPrimaryKeySelective(tag);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.TAG_DELETE_FAILED);
        }
    }
}
