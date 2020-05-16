package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * @author Fredy
 * @date 2020/5/15 11:29
 */
@Api("博客展示")
@RestController
public class BlogShowController {
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;

    /**
     * 功能描述：获取全部类型(map)
     * @return
     */
    @ApiOperation("获取全部类型(map)")
    @GetMapping("typesMap")
    public RestResult typesMap(){
        return ResultGenerator.getSuccessResult(typeService.getAllTypes());
    }

    /**
     * 功能描述：获取全部标签(map)
     * @return
     */
    @ApiOperation("获取全部标签(map)")
    @GetMapping("tagsMap")
    public RestResult tagsMap(){
        return ResultGenerator.getSuccessResult(tagService.getAllTags());
    }
}
