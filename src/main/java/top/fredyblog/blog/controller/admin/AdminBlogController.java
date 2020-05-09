package top.fredyblog.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * 管理员：博客管理
 * @author Fredy
 * @date 2020/5/9 22:24
 */
@Api("管理员博客管理模块")
@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminBlogController {
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
     * @return
     */
    @ApiOperation("博客列表查询")
    @GetMapping("/blogs")
    public RestResult blogs(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        BlogExample blogExample = new BlogExample();
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, null));
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
                             String blogTitle, Integer typeId, Integer tagId){
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        if (typeId != null) {
            criteria.andTypeIdEqualTo(typeId);
        }
        if (tagId != null) {
            criteria.andTagIdStrLike("%," + tagId + ",%");
        }
        if(StringUtils.isNotBlank(blogTitle)){
            criteria.andBlogTitleLike("%," + blogTitle + ",%");
        }
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, null));
    }

    /**
     * 功能描述：驳回博客
     * @param id
     * @return
     */
    @ApiOperation("驳回博客")
    @PutMapping("/blogs/{id}/reject")
    public RestResult rejectBlog(@PathVariable Integer id){
        blogService.rejectBlog(id);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除博客
     * @param id
     * @return
     */
    @ApiOperation("删除博客")
    @DeleteMapping("/blogs/{id}")
    public RestResult deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return ResultGenerator.getSuccessResult();
    }
}
