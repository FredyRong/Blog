package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * 类型控制层
 * @author Fredy
 * @date 2020/5/16 15:26
 */
@Api("类型控制层")
@RestController
public class TypeController {
    @Resource
    private BlogService blogService;
    @Resource
    private TypeService typeService;

    /**
     * 获取热门类型
     * @return
     */
    @ApiOperation("获取热门类型")
    @GetMapping("/topType")
    public RestResult topTag(@RequestParam(defaultValue = "1000", required = false) Integer size){
        return ResultGenerator.getSuccessResult(blogService.getTopTypeList(size));
    }

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
     * 功能描述：获取全部类型(list)
     * @return
     */
    @ApiOperation("获取全部类型(list)")
    @GetMapping("typesList")
    public RestResult typesList(){
        return ResultGenerator.getSuccessResult(typeService.getAll());
    }
}
