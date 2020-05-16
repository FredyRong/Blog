package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.ReplyService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 回复管理控制层
 * @author Fredy
 * @date 2020/5/16 13:55
 */
@Api("回复管理控制层")
@RestController
public class ReplyController {
    @Resource
    private ReplyService replyService;

    /**
     * 功能描述：新增评论
     * @param commentId
     * @param blogId
     * @param content
     * @param repliedUserId
     * @param replyType
     * @param session
     * @return
     */
    @ApiOperation("新增评论")
    @PostMapping("/replys")
    public RestResult addReplys(Integer commentId, Integer blogId, String content,
                                Integer repliedUserId, String replyType, HttpSession session){
        User user = (User) session.getAttribute("user");
        replyService.addReplys(commentId, blogId, content, user, repliedUserId,replyType);
        return ResultGenerator.getSuccessResult();
    }
}
