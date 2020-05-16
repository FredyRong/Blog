package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * 标签控制层
 * @author Fredy
 * @date 2020/5/16 15:05
 */
@Api("标签控制层")
@RestController
public class TagController {
    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;

    /**
     * 获取热门标签
     * @return
     */
    @ApiOperation("获取热门标签")
    @GetMapping("/topTag")
    public RestResult topTag(@RequestParam(defaultValue = "1000", required = false) Integer size){
        return ResultGenerator.getSuccessResult(blogService.getTopTagList(size));
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

    /**
     * 功能描述：获取全部标签(list)
     * @return
     */
    @ApiOperation("获取全部标签(list)")
    @GetMapping("tagsList")
    public RestResult tagsList(){
        return ResultGenerator.getSuccessResult(tagService.getAll());
    }
}
