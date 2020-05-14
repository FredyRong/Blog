package top.fredyblog.blog.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.entity.Type;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客管理
 * @author Fredy
 * @date 2020/5/14 22:59
 */
@Api("博客管理")
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;

    /**
     * 功能描述：博客列表查询
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @ApiOperation("博客列表查询")
    @GetMapping("/blogs")
    public RestResult blogs(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            HttpSession session){
        User user = (User) session.getAttribute("user");
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andUserIdEqualTo(user.getUserId());
        blogExample.setOrderByClause("update_time desc");
        return ResultGenerator.getSuccessResult( blogService.page(blogExample, pageNum, pageSize,null));
    }

    /**
     * 功能描述：获取全部类型(map)
     * @return
     */
    @ApiOperation("获取全部类型(map)")
    @GetMapping("getTypesMap")
    public RestResult typesMap(){
        List<Type> typeList = typeService.getAll();
        return ResultGenerator.getSuccessResult(typeList.stream().collect(Collectors.toMap(Type::getTypeId, Type::getTypeName, (key1, key2) -> key2)));
    }

    /**
     * 功能描述：获取全部类型(list)
     * @return
     */
    @ApiOperation("获取全部类型(list)")
    @GetMapping("getTypesList")
    public RestResult typesList(){
        return ResultGenerator.getSuccessResult(typeService.getAll());
    }

    /**
     * 功能描述：获取全部标签(list)
     * @return
     */
    @ApiOperation("获取全部标签(list)")
    @GetMapping("getTagsList")
    public RestResult tagsList(){
        return ResultGenerator.getSuccessResult(tagService.getAll());
    }

    /**
     * 功能描述：博客搜索
     * @param pageNum
     * @param pageSize
     * @param blogTitle
     * @param typeId
     * @param tagId
     * @return
     */
    @ApiOperation("博客搜索")
    @PostMapping("/blogs/search")
    public RestResult search(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String blogTitle, Integer typeId, Integer tagId, HttpSession session){
        User user = (User) session.getAttribute("user");
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        if (StringUtils.isNotBlank(blogTitle)) {
            criteria.andBlogTitleLike("%" + blogTitle + "%");
        }
        if (typeId != null) {
            criteria.andTypeIdEqualTo(typeId);
        }
        if (tagId != null) {
            criteria.andTagIdStrLike("%," + tagId + ",%");
        }
        blogExample.setOrderByClause("update_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, null));
    }

    /**
     * 功能描述：博客单个查询
     * @param id
     * @return
     */
    @ApiOperation("博客单个查询")
    @GetMapping("/blog/{id}")
    public RestResult getBlog(@PathVariable Integer id){
        return ResultGenerator.getSuccessResult(blogService.findOne(id));
    }

    /**
     * 功能描述：提交博客信息
     * @param blog
     * @param session
     * @return
     */
    @ApiOperation("提交博客信息")
    @PostMapping("/blog")
    @PutMapping("/blog")
    public RestResult updateBlog(Blog blog, HttpSession session){
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getUserId());
        if (blog.getBlogId() == null) {
            blogService.addBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除博客
     * @param id
     * @return
     */
    @ApiOperation("删除博客")
    @DeleteMapping("/blog/{id}")
    public RestResult deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：个人博客归档
     * @param session
     * @return
     */
    @ApiOperation("个人博客归档")
    @GetMapping("/archives")
    public RestResult archives(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResultGenerator.getSuccessResult(blogService.archiveBlog(user.getUserId()));
    }

    /**
     * 功能描述：获取用户博客数
     * @param session
     * @return
     */
    @ApiOperation("获取用户博客数")
    @GetMapping("/blogCount")
    public RestResult blogCount(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResultGenerator.getSuccessResult(blogService.countBlog(user.getUserId()));
    }
}
