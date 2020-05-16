package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.service.TypeService;

import javax.annotation.Resource;

/**
 * @author Fredy
 * @date 2020/5/15 11:48
 */
@Api("首页控制器")
@RestController
public class IndexController {
    @Resource
    private BlogService blogService;
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;
//    @Resource
//    private EsBlogService esBlogService;


}
