package top.fredyblog.blog.model.es.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.fredyblog.blog.constant.RedisConstant;
import top.fredyblog.blog.mapper.BlogTagMapper;
import top.fredyblog.blog.mapper.UserMapper;
import top.fredyblog.blog.model.entity.BlogTagExample;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.es.EsBlogDo;
import top.fredyblog.blog.model.es.EsBlogRepository;
import top.fredyblog.blog.model.es.EsBlogService;
import top.fredyblog.blog.utils.RedisValueUtil;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * @author Fredy
 * @date 2020/5/16 18:07
 */
@Service
@Log4j2
public class EsBlogServiceImpl implements EsBlogService {
    @Resource
    private EsBlogRepository esBlogRepository;
    @Resource
    private RedisValueUtil redisValueUtil;
    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 功能描述：标题关键字搜索
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public Page<EsBlogDo> search(String keyword, Integer pageNum, Integer pageSize, Integer userId) {
        //参数不合法调整
        if (pageNum < 0) {
            pageNum = 0;
        }
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.matchQuery("delFlag", false))
                .must(QueryBuilders.matchQuery("published", true));
        if (StringUtils.isNotBlank(keyword)) {
            //组装模糊查询条件
            query.must(QueryBuilders.fuzzyQuery("blogTitle", keyword.toLowerCase()));
        }
        Page<EsBlogDo> page = esBlogRepository.search(query, PageRequest.of(pageNum, pageSize,
                Sort.by(Sort.Direction.DESC, "createTime")));
        for (EsBlogDo esBlogDo : page) {
            BlogTagExample blogTagExample = new BlogTagExample();
            blogTagExample.createCriteria().andBlogIdEqualTo(esBlogDo.getBlogId());
            esBlogDo.setTags(blogTagMapper.selectByExample(blogTagExample));
            User user = userMapper.selectByPrimaryKey(esBlogDo.getUserId());
            esBlogDo.setNickname(user.getNickname());
            esBlogDo.setHeadPortrait(user.getHeadPortrait());
            //从缓存中取博客评论数和浏览数
            if (redisValueUtil.hExists(RedisConstant.COMMENT_KEY.getDesc(),
                    esBlogDo.getBlogId().toString())) {
                esBlogDo.setBlogViews((Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY.getDesc(),
                        esBlogDo.getBlogId().toString()));
            }
            if (redisValueUtil.hExists(RedisConstant.COMMENT_KEY.getDesc(),
                    esBlogDo.getBlogId().toString())) {
                esBlogDo.setBlogComments((Integer) redisValueUtil.hGet(RedisConstant.COMMENT_KEY.getDesc(),
                        esBlogDo.getBlogId().toString()));
            }
            if (redisValueUtil.hExists(RedisConstant.LIKE_KEY.getDesc(),
                    esBlogDo.getBlogId().toString())) {
                HashSet<Integer> set =
                        (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY.getDesc(),
                                esBlogDo.getBlogId().toString());
                esBlogDo.setBlogLikes(set.size());
                if (userId == null) {
                    esBlogDo.setFlag(false);
                } else {
                    esBlogDo.setFlag(set.contains(userId));
                }
                log.info("更新博客编号[{}]的点赞数为redis最新点赞数据：{}", esBlogDo.getBlogId(),
                        set.size());
            }
        }
        return page;
    }
}
