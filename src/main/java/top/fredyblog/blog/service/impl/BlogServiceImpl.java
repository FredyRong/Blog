package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fredyblog.blog.constant.RedisConstant;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.BlogExtendMapper;
import top.fredyblog.blog.mapper.BlogMapper;
import top.fredyblog.blog.mapper.BlogTagExtendMapper;
import top.fredyblog.blog.mapper.BlogTagMapper;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.entity.BlogTag;
import top.fredyblog.blog.model.entity.BlogTagExample;
import top.fredyblog.blog.model.pojo.TopTag;
import top.fredyblog.blog.model.pojo.TopType;
import top.fredyblog.blog.model.pojo.TopUser;
import top.fredyblog.blog.model.vo.BlogVo;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.utils.MarkdownUtils;
import top.fredyblog.blog.utils.RedisValueUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 博客管理服务实现类
 * @author Fredy
 * @date 2020/5/6 23:15
 */
@Service
@Log4j2
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogExtendMapper blogExtendMapper;
    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private BlogTagExtendMapper blogTagExtendMapper;
//    @Resource
//    private EsBlogRepository esBlogRepository;
    @Resource
    private RedisValueUtil redisValueUtil;

    /**
     * 功能描述：分页查询博客信息
     * @param blogExample
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogVo> list = blogExtendMapper.page(blogExample);
        for (BlogVo blogVo : list) {
            BlogTagExample blogTagExample = new BlogTagExample();
            blogExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
            blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
            //从redis中取博客评论数、浏览数、点赞数
            if(redisValueUtil.hExists(RedisConstant.COMMENT_KEY.getDesc(), blogVo.getBlogId().toString())){
                Integer blogComments = (Integer)redisValueUtil.hGet(RedisConstant.COMMENT_KEY.getDesc(), blogVo.getBlogId().toString());
                blogVo.setBlogComments(blogComments);
                log.info("更新博客编号[{}]的评论数为redis最新评论数据：{}", blogVo.getBlogId(), blogComments);
            }
            if(redisValueUtil.hExists(RedisConstant.VIEW_KEY.getDesc(), blogVo.getBlogId().toString())){
                Integer blogViews = (Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY.getDesc(), blogVo.getBlogId().toString());
                blogVo.setBlogViews(blogViews);
                log.info("更新博客编号[{}]的浏览数为redis最新浏览数据：{}", blogVo.getBlogId(), blogViews);
            }
            if(redisValueUtil.hExists(RedisConstant.LIKE_KEY.getDesc(), blogVo.getBlogId().toString())) {
                HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY.getDesc(), blogVo.getBlogId().toString());
                blogVo.setBlogLikes(set.size());
                if (userId == null) {
                    blogVo.setFlag(false);
                }else{
                    blogVo.setFlag(set.contains(userId));
                }
                log.info("更新博客编号[{}]的点赞数为redis最新点赞数据：{}", blogVo.getBlogId(), set.size());
            }
        }
        return PageInfo.of(list);
    }

    /**
     * 功能描述：查询所有博客信息
     * @return
     */
    @Override
    public List<Blog> getAll() {
        return blogMapper.selectByExample(new BlogExample());
    }

    /**
     * 功能描述：查询指定数量的top类型的博客
     * @param topSize
     * @return
     */
    @Override
    public List<TopType> getTopTypeList(Integer topSize) {
        List<TopType> topTypeList = blogExtendMapper.getTopTypeList(topSize);
        return topTypeList.stream().filter(s -> s.getBlogNums() > 0).collect(Collectors.toList());
    }

    /**
     * 功能描述：查询指定数量的推荐的博客（根据博客浏览数）
     * @param topSize
     * @return
     */
    @Override
    public List<Blog> getRecommendBlogs(Integer topSize) {
        return blogExtendMapper.getRecommendBlogs(topSize);
    }

    /**
     * 功能描述：查询指定数量的top标签的博客
     * @param topSize
     * @return
     */
    @Override
    public List<TopTag> getTopTagList(Integer topSize) {
        return blogTagExtendMapper.getTopTagList(topSize);
    }

    /**
     * 功能描述：获取指定数量的最新博客基本信息
     * @param topSize
     * @return
     */
    @Override
    public List<Blog> getLastUpdateBlogTop(Integer topSize) {
        return blogExtendMapper.getLastUpdateBlogTop(topSize);
    }

    /**
     * 功能描述：获取博客详情
     * @param blogId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo viewBlogDetail(Integer blogId, Integer userId) {
        //查询博客详情
        BlogVo blogVo = blogExtendMapper.getBlogDeatil(blogId);
        if (blogVo == null) {
            throw new CustomizeException(CustomizeErrorCode.BLOG_NOT_FOUND);
        }
        //将markdown内容转换成html
        blogVo.setBlogContent(MarkdownUtils.markdownToHtmlExtensions(blogVo.getBlogContent()));
        //查询博客的标签列表
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
        blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
        blogVo.setBlogComments((Integer) redisValueUtil.hGet(RedisConstant.COMMENT_KEY.getDesc(), blogId.toString()));
        blogVo.setBlogViews((Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY.getDesc(), blogId.toString()));
        HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY.getDesc(), blogId.toString());
        blogVo.setBlogLikes(set.size());
        if (userId == null) {
            blogVo.setFlag(false);
        }else{
            blogVo.setFlag(set.contains(userId));
        }
        redisValueUtil.hIncr(RedisConstant.VIEW_KEY.getDesc(), blogId.toString());
        return blogVo;
    }

    /**
     * 功能描述：单条查询博客基本信息
     * @param blogId
     * @return
     */
    @Override
    public Blog findOne(Integer blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        return blog;
    }

    /**
     * 功能描述：更新博客的评论数
     * @param blogId
     */
    @Override
    public void updateBlogComments(Integer blogId) {
        redisValueUtil.hIncr(RedisConstant.COMMENT_KEY.getDesc(), blogId.toString());
    }

    /**
     * 功能描述：博客归档
     * @param userId
     * @return
     */
    @Override
    public Map<String, List<Blog>> archiveBlog(Integer userId) {
        List<String> years = blogExtendMapper.findGroupYear(userId);
        Map<String, List<Blog>> map = new HashMap<>(years.size());
        for (String year : years) {
            map.put(year, blogExtendMapper.findByYear(year, userId));
        }
        return map;
    }

    /**
     * 功能描述：查询博客总数
     * @param userId
     * @return
     */
    @Override
    public Long countBlog(Integer userId) {
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria().andDelFlagEqualTo(false).andPublishedEqualTo(true);
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        return blogMapper.countByExample(blogExample);
    }

    /**
     * 功能描述：新增博客
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBlog(Blog blog) {
        if (blog.getRecommend() == null) {
            blog.setRecommend(false);
        }
        if (blog.getAppreciationFlag() == null) {
            blog.setAppreciationFlag(false);
        }
        if (blog.getCommentabled() == null) {
            blog.setCommentabled(false);
        }
        if (blog.getShareFlag() == null) {
            blog.setShareFlag(false);
        }
        if (StringUtils.isBlank(blog.getBlogFlag())) {
            blog.setBlogFlag("原创");
        }
        String tagIdStr = blog.getTagIdStr();
        blog.setBlogViews(0);
        blog.setTagIdStr("," + tagIdStr + ",");
        blog.setBlogComments(0);
        blog.setBlogLikes(0);
        LocalDateTime now = LocalDateTime.now();
        blog.setCreateTime(now);
        blog.setUpdateTime(now);
        blog.setDelFlag(false);
        int n = blogMapper.insertSelective(blog);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.BLOG_INSERT_FAILED);
        }
        //新增博客标签对应关系
        String[] tagIds = tagIdStr.split(",");
        updateBlogTag(blog.getBlogId(), tagIds, blog.getPublished());
        log.info("redis新增博客编号[{}]的基本信息>>>>>>start", blog.getBlogId());
        redisValueUtil.hPut(RedisConstant.COMMENT_KEY.getDesc(), blog.getBlogId().toString(), 0);
        redisValueUtil.hPut(RedisConstant.VIEW_KEY.getDesc(), blog.getBlogId().toString(), 0);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY.getDesc(), blog.getBlogId().toString(), new HashSet<Integer>());
        log.info("redis新增博客编号[{}]的基本信息>>>>>>end", blog.getBlogId());
    }

    /**
     * 功能描述：更新博客
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Blog blog) {
        //更新博客
        if (blog.getRecommend() == null) {
            blog.setRecommend(false);
        }
        if (blog.getShareFlag() == null) {
            blog.setShareFlag(false);
        }
        if (blog.getAppreciationFlag() == null) {
            blog.setAppreciationFlag(false);
        }
        if (blog.getCommentabled() == null) {
            blog.setCommentabled(false);
        }
        if (!blog.getTagIdStr().startsWith(",")) {
            blog.setTagIdStr("," + blog.getTagIdStr());
        }
        String tagIdStr = blog.getTagIdStr();
        String[] tagIds = tagIdStr.substring(1).split(",");
        blog.setTagIdStr(tagIdStr + ",");
        blog.setUpdateTime(LocalDateTime.now());
        blog.setDelFlag(false);
        int n = blogMapper.updateByPrimaryKeySelective(blog);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.BLOG_INSERT_FAILED);
        }
        //更新博客标签对应关系
        updateBlogTag(blog.getBlogId(), tagIds, blog.getPublished());
    }

    /**
     * 功能描述：更新博客标签对应关系
     * @param blogId
     * @param tagIds
     * @param published
     */
    private void updateBlogTag(Integer blogId, String[] tagIds, Boolean published) {
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogId);
        blogTagMapper.deleteByExample(blogTagExample);
        //只有发布才将标签信息写入关系表中
        if (published) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(blogId);
            for (String tagId : tagIds) {
                blogTag.setTagId(Integer.parseInt(tagId));
                int n = blogTagMapper.insertSelective(blogTag);
                if(n != 1){
                    throw new CustomizeException(CustomizeErrorCode.BLOG_TAG_INSERT_FAILED);
                }
            }
        }
    }

    /**
     * 功能描述：删除博客
     * @param id
     */
    @Override
    public void deleteBlog(Integer id) {
        Blog blog = new Blog();
        LocalDateTime now = LocalDateTime.now();
        blog.setBlogId(id);
        blog.setDelFlag(true);
        blog.setDelTime(now);
        blog.setUpdateTime(now);
        int n = blogMapper.updateByPrimaryKeySelective(blog);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.BLOG_DELETE_FAILED);
        }
    }

    /**
     * 功能描述：驳回博客
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectBlog(Integer id) {
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setPublished(false);
        blog.setUpdateTime(LocalDateTime.now());
        int n1 = blogMapper.updateByPrimaryKeySelective(blog);
        if(n1 != 1){
            throw new CustomizeException(CustomizeErrorCode.BLOG_UPDATE_FAILED);
        }
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(id);
        int n2 = blogTagMapper.deleteByExample(blogTagExample);
        if(n2 != 1){
            throw new CustomizeException(CustomizeErrorCode.BLOG_TAG_DELETE_FAILED);
        }
    }

    /**
     * 功能描述：同步博客信息至es中
     * @return
     */
    @Override
    public Integer syncBlogToEs() {
        return null;
    }

    /**
     * 功能描述：同步博客评论数和浏览数
     */
    @Override
    public void syncBlogInfoFromRedis() {

    }

    /**
     * 功能描述：点赞
     * @param blogId
     * @param userId
     */
    @Override
    public void addThumbsUp(Integer blogId, Integer userId) {
        HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY.getDesc(), blogId.toString());
        set.add(userId);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY.getDesc(), blogId.toString(), set);
        log.info("博客编号[{}]新增点赞用户编号[{}]", blogId, userId);
    }

    /**
     * 功能描述：取消点赞
     * @param blogId
     * @param userId
     */
    @Override
    public void cancelThumbsUp(Integer blogId, Integer userId) {
        HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY.getDesc(), blogId.toString());
        set.remove(userId);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY.getDesc(), blogId.toString(), set);
        log.info("博客编号[{}]删除点赞用户编号[{}]", blogId, userId);
    }

    /**
     * 功能描述：获取指定数量用户排行榜
     * @param topSize
     * @return
     */
    @Override
    public List<TopUser> getTopUserList(Integer topSize) {
        return blogExtendMapper.getTopUserList(topSize);
    }
}
