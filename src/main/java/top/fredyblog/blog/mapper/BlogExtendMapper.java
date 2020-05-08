package top.fredyblog.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.pojo.TopType;
import top.fredyblog.blog.model.pojo.TopUser;
import top.fredyblog.blog.model.vo.BlogVo;

import java.util.List;

/**
 * 博客拓展mapper
 * @author Fredy
 * @date 2020/5/6 23:20
 */
public interface BlogExtendMapper {
    /**
     * 功能描述：首页展示博客列表
     * @param blogExample
     * @return
     */
    List<BlogVo> page(BlogExample blogExample);

    /**
     * 功能描述：查询指定数量的top类型的博客数
     * @param topSize
     * @return
     */
    List<TopType> getTopTypeList(@Param("topSize")Integer topSize);

    /**
     * 功能描述：查询指定数量的推荐博客
     * @param topSize
     * @return
     */
    List<Blog> getRecommendBlogs(@Param("topSize")Integer topSize);

    /**
     * 功能描述：查询指定数量的最新博客
     * @param topSize
     * @return
     */
    List<Blog> getLastUpdateBlogTop(@Param("topSize")Integer topSize);

    /**
     * 功能描述：博客详情查看
     * @param blogId
     * @return
     */
    BlogVo getBlogDeatil(@Param("blogId")Integer blogId);

    /**
     * 功能描述：查询博客包含的年份
     * @param userId
     * @return
     */
    List<String> findGroupYear(@Param("userId") Integer userId);

    /**
     * 功能描述：根据年份查看博客数据
     * @param year
     * @param userId
     * @return
     */
    List<Blog> findByYear(@Param("year") String year, @Param("userId") Integer userId);

    /**
     * 功能描述：主键查询（不带博客正文内容）
     * @param blogId
     * @return
     */
    Blog getBlogWithoutContent(@Param("blogId")Integer blogId);

    /**
     * 功能描述：获取指定数量用户排行榜
     * @param topSize
     * @return
     */
    List<TopUser> getTopUserList(Integer topSize);
}
