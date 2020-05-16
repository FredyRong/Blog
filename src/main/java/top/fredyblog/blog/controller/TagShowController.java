package top.fredyblog.blog.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.BlogExample;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.pojo.TopTag;
import top.fredyblog.blog.service.BlogService;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 功能描述：标签管理
 * @author Fredy
 * @date 2020/5/16 13:51
 */
@ApiOperation("博客标签展示")
@RestController
public class TagShowController {
    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;
    @Resource
    private TypeService typeService;

    /**
     * 获取热门类型
     * @return
     */
    @ApiOperation("获取热门标签")
    @GetMapping("/topTag")
    public RestResult topType(){
        return ResultGenerator.getSuccessResult(blogService.getTopTagList(10000));
    }

    /**
     * 功能描述：同标签下博客分页查询
     * @param pageNum
     * @param pageSize
     * @param id
     * @param session
     * @return
     */
    @ApiOperation("同类型下博客分页查询")
    @GetMapping("/blog/type/{id}")
    public RestResult getBlogWithType(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @PathVariable Integer id,
                                      HttpSession session) {
        if (id == -1) {
            List<TopTag> tags = blogService.getTopTagList(10000);
            id = tags.get(0).getTagId();
        }
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTagIdStrLike("%," + id + ",%").andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(blogService.page(blogExample, pageNum, pageSize, userId));
    }
}
