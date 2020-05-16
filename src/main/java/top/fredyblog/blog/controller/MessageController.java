package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.MessageService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 留言控制层
 * @author Fredy
 * @date 2020/5/16 15:29
 */
@Api("留言控制层")
@RestController
public class MessageController {
    @Resource
    private MessageService messageService;

    /**
     * 功能描述：留言分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("留言分页查询")
    @GetMapping("/message")
    public RestResult message(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return ResultGenerator.getSuccessResult(messageService.page(pageNum, pageSize));
    }

    /**
     * 功能描述：留言添加
     * @param nickname
     * @param email
     * @param content
     * @param session
     * @return
     */
    @ApiOperation("留言添加")
    @PostMapping("/message")
    public RestResult addMessage(String nickname, String email, String content, HttpSession session){
        User user = (User) session.getAttribute("user");
        messageService.addMessage(nickname, email, content, user);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：新增留言的回复
     * @param nickname
     * @param email
     * @param content
     * @param messageId
     * @param repliedUserId
     * @param repliedUserNickname
     * @param session
     * @return
     */
    @ApiOperation("新增留言的回复")
    @PostMapping("/addMessageReply")
    public RestResult addMessageReply(String nickname, String email, String content, Integer messageId,
                                      Integer repliedUserId, String repliedUserNickname, HttpSession session){
        User user = (User) session.getAttribute("user");
        messageService.addMessageReply(nickname, email, content, messageId, repliedUserId, repliedUserNickname, user);
        return ResultGenerator.getSuccessResult();
    }

}
