package top.fredyblog.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Tag;
import top.fredyblog.blog.model.entity.TagExample;
import top.fredyblog.blog.service.TagService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员：标签管理
 * @author Fredy
 * @date 2020/5/7 18:27
 */
@Api("管理员标签管理模块")
@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminTagController {
    @Resource
    private TagService tagService;

    /**
     * 功能描述：标签分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("标签分页查询")
    @GetMapping("/tags")
    public RestResult tags(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        tagExample.setOrderByClause("create_time");
        PageInfo<Tag> page = tagService.page(tagExample, pageNum, pageSize);
        return ResultGenerator.getSuccessResult(page);
    }

    /**
     * 功能描述：标签单个查询
     * @param id
     * @return
     */
    @ApiOperation("标签单个查询")
    @GetMapping("/tag/{id}")
    public RestResult getTag(@PathVariable Integer id){
        Tag tag = tagService.findOne(id);
        if(tag == null){
            throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
        }
        return ResultGenerator.getSuccessResult(tag);
    }

    /**
     * 功能描述：提交标签信息
     * @param tag
     * @param result
     * @return
     */
    @ApiOperation("提交标签信息")
    @PostMapping("/tag")
    public RestResult postTag(@Valid Tag tag, BindingResult result){
        Tag tagByName = tagService.findTagByName(tag.getTagName());
        if (tagByName != null) {
            throw new CustomizeException(CustomizeErrorCode.TAG_EXISTED);
        }
        if(result.hasErrors()){
            List<String> errorMessage = new ArrayList<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.add(fieldError.getDefaultMessage() + ",");
            }
            return ResultGenerator.getFailResult("validation error", errorMessage);
        }
        tagService.saveTag(tag);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：更新标签信息
     * @param id
     * @param tag
     * @return
     */
    @ApiOperation("更新标签信息")
    @PutMapping("/tag/{id}")
    public RestResult postTag(@PathVariable Integer id, Tag tag){
        Tag tag1 = tagService.findOne(id);
        tagService.saveTag(tag);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除标签信息
     * @param id
     * @return
     */
    @ApiOperation("删除标签信息")
    @DeleteMapping("/tag/{id}")
    public RestResult deleteTag(@PathVariable Integer id){
        tagService.deleteTag(id);
        return ResultGenerator.getSuccessResult();
    }
}
