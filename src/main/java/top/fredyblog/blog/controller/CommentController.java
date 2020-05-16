package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.CommentService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 评论控制层
 * @author Fredy
 * @date 2020/5/15 11:39
 */
@Api("评论控制层")
@RestController
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    /**
     * 功能描述：评论分页获取
     * @param blogId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("评论分页获取")
    @GetMapping("/comment/{blogId}")
    public RestResult getComments(@PathVariable Integer blogId,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        return ResultGenerator.getSuccessResult(commentService.getComments(blogId, pageNum, pageSize));
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
     * 功能描述：新增评论
     * @param blogId
     * @param content
     * @param session
     * @return
     */
    @ApiOperation("新增评论")
    @PostMapping("/comment")
    public RestResult addComment(Integer blogId, String content, HttpSession session){
        User user = (User) session.getAttribute("user");
        commentService.addComments(blogId, content, user);
        return ResultGenerator.getSuccessResult();
    }
}
