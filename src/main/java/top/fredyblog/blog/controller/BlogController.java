package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.es.EsBlogService;
import top.fredyblog.blog.model.pojo.TopTag;
import top.fredyblog.blog.model.pojo.TopType;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Fredy
 * @date 2020/5/16 15:34
 */
@Api("博客控制层")
@RestController
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private EsBlogService esBlogService;

    /**
     * 功能描述：个人博客归档
     * @return
     */
    @ApiOperation("个人博客归档")
    @GetMapping("/archives")
    public RestResult archives(){
        return ResultGenerator.getSuccessResult(blogService.archiveBlog(null));
    }

    /**
     * 功能描述：获取用户博客数
     * @return
     */
    @ApiOperation("获取用户博客数")
    @GetMapping("/blogCount")
    public RestResult blogCount(HttpSession session){
        return ResultGenerator.getSuccessResult(blogService.countBlog(null));
    }

    /**
     * 功能描述：获取博客作者id
     * @param blogId
     * @return
     */
    @ApiOperation("获取博客作者id")
    @GetMapping("/blogUserId/{blogId}")
    public RestResult getBlogUserId(@PathVariable Integer blogId){
        Blog blog = blogService.findOne(blogId);
        if (blog == null) {
            throw new CustomizeException(CustomizeErrorCode.BLOG_NOT_FOUND);
        }
        return ResultGenerator.getSuccessResult(blog.getUserId());
    }

    /**
     * 功能描述：博客分页查询
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @ApiOperation("博客分页查询")
    @GetMapping("/blog")
    public RestResult blog(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, userId));
    }

    /**
     * 功能描述：同标签下博客分页查询
     * @param pageNum
     * @param pageSize
     * @param id
     * @param session
     * @return
     */
    @ApiOperation("同类型下博客分页查询")
    @GetMapping("/blog/type/{id}")
    public RestResult getBlogsWithType(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @PathVariable Integer id,
                                      HttpSession session) {
        if (id == -1) {
            List<TopTag> tags = blogService.getTopTagList(10000);
            id = tags.get(0).getTagId();
        }
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTagIdStrLike("%," + id + ",%").andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, userId));
    }

    /**
     * 功能描述：同类型下博客分页查询
     * @param pageNum
     * @param pageSize
     * @param id
     * @param session
     * @return
     */
    @ApiOperation("同类型下博客分页查询")
    @GetMapping("/blog/type/{id}")
    public RestResult getBlogsWithTag(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @PathVariable Integer id,
                                      HttpSession session){
        if (id == -1) {
            List<TopType> types = blogService.getTopTypeList(10000);
            id = types.get(0).getTypeId();
        }
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTypeIdEqualTo(id).andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, userId));
    }

    /**
     * 功能描述：获取推荐博客
     * @param size
     * @return
     */
    @ApiOperation("获取推荐博客")
    @GetMapping("/recommendBlogs")
    public RestResult getRecommendBlogs(@RequestParam(defaultValue = "8") Integer size){
        return ResultGenerator.getSuccessResult(blogService.getRecommendBlogs(size));
    }

    /**
     * 功能描述：获取最新博客
     * @param size
     * @return
     */
    @ApiOperation("获取最新博客")
    @GetMapping("/lastUpdateBlogTop")
    public RestResult getLastUpdateBlogTop(@RequestParam(defaultValue = "3") Integer size){
        return ResultGenerator.getSuccessResult(blogService.getLastUpdateBlogTop(size));
    }

    /**
     * 功能描述：获取推荐用户
     * @param size
     * @return
     */
    @ApiOperation("获取推荐用户")
    @GetMapping("/recommendUsers")
    public RestResult getRecommendUsers(@RequestParam(defaultValue = "8") Integer size){
        return ResultGenerator.getSuccessResult(blogService.getTopUserList(size));
    }

    /**
     * 功能描述：搜索标题关键字
     * @param pageNum
     * @param pageSize
     * @param searchKey
     * @param session
     * @return
     */
    @ApiOperation("搜索标题关键字")
    @GetMapping("/search")
    public RestResult search(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String searchKey, HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        return ResultGenerator.getSuccessResult(esBlogService.search(searchKey, pageNum - 1, pageSize, userId));
    }

    /**
     * 功能描述：点赞/取消点赞
     * @param blogId
     * @param flag
     * @param session
     * @return
     */
    @ApiOperation("点赞/取消点赞")
    @PutMapping("/thumbsUpOrDown")
    public RestResult thumbsUpOrDown(Integer blogId, Boolean flag, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOADED);
        }else{
            if(flag){
                blogService.addThumbsUp(blogId, user.getUserId());
            }else{
                blogService.cancelThumbsUp(blogId, user.getUserId());
            }
        }
        return ResultGenerator.getSuccessResult();
    }
}
