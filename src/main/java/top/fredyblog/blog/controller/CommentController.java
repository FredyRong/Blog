package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.CommentService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 评论控制层
 * @author Fredy
 * @date 2020/5/16 15:31
 */
@Api("评论控制层")
@RestController
public class CommentController {
    @Resource
    private CommentService commentService;

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
