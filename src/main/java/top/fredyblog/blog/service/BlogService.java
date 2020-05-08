package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.pojo.TopTag;
import top.fredyblog.blog.model.pojo.TopType;
import top.fredyblog.blog.model.pojo.TopUser;
import top.fredyblog.blog.model.vo.BlogVo;

import java.util.List;
import java.util.Map;

/**
 * 博客管理服务实现类
 * @author Fredy
 * @date 2020/5/6 22:53
 */
public interface BlogService {
    /**
     * 功能描述：分页查询博客信息
     * @param blogExample
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum, Integer pageSize, Integer userId);

    /**
     * 功能描述：获取所有博客信息
     * @return
     */
    List<Blog> getAll();

    /**
     * 功能描述：查询指定数量的top博客类型
     * @param topSize
     * @return
     */
    List<TopType> getTopTypeList(Integer topSize);

    /**
     * 功能描述：查询指定数量推荐的博客
     * @param topSize
     * @return
     */
    List<Blog> getRecommendBlogs(Integer topSize);

    /**
     * 功能描述：查询指定数量的top标签的博客数
     * @param topSize
     * @return
     */
    List<TopTag> getTopTagList(Integer topSize);

    /**
     * 功能描述：获取指定数量的最新博客基本信息
     * @param topSize
     * @return
     */
    List<Blog> getLastUpdateBlogTop(Integer topSize);

    /**
     * 功能描述：博客详情查看
     * @param blogId
     * @param userId
     * @return
     */
    BlogVo viewBlogDetail(Integer blogId,Integer userId);

    /**
     * 功能描述：单条查看博客基本信息
     * @param blogId
     * @return
     */
    Blog findOne(Integer blogId);

    /**
     * 功能描述：更新博客评论数
     * @param blogId
     */
    void updateBlogComments(Integer blogId);

    /**
     * 功能描述：博客归档
     * @param userId
     * @return
     */
    Map<String, List<Blog>> archiveBlog(Integer userId);

    /**
     * 功能描述：查询博客总数
     * @param userId
     * @return
     */
    Long countBlog(Integer userId);

    /**
     * 功能描述：新增博客
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 功能描述：更新博客信息
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 功能描述：删除博客
     * @param id
     */
    void deleteBlog(Integer id);

    /**
     * 功能描述：驳回博客
     * @param id
     */
    void rejectBlog(Integer id);

    /**
     * 功能描述：同步博客信息至es中
     * @return
     */
    Integer syncBlogToEs();

    /**
     * 功能描述：同步博客评论数和浏览数
     */
    void syncBlogInfoFromRedis();

    /**
     * 功能描述：点赞
     * @param blogId
     * @param userId
     */
    void addThumbsUp(Integer blogId,Integer userId);

    /**
     * 功能描述：取消点赞
     * @param blogId
     * @param userId
     */
    void cancelThumbsUp(Integer blogId,Integer userId);

    /**
     * 功能描述：获取指定数量用户排行榜
     * @param topSize
     * @return
     */
    List<TopUser> getTopUserList(Integer topSize);
}
