package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 归档管理
 * @author Fredy
 * @date 2020/5/15 11:27
 */
@Api("归档管理")
@RestController
public class ArchiveController {
    @Resource
    private BlogService blogService;

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
}
