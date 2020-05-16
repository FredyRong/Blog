package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Announcement;
import top.fredyblog.blog.service.AnnouncementService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * 公告控制层
 * @author Fredy
 * @date 2020/5/16 15:32
 */
@Api("公告控制层")
@RestController
public class AnnouncementController {
    @Resource
    private AnnouncementService announcementService;

    /**
     * 功能描述：公告分页获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("公告分页获取")
    @GetMapping("/anncs")
    public RestResult anncs(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false, defaultValue = "10")Integer pageSize){
        return ResultGenerator.getSuccessResult(announcementService.page(pageNum, pageSize));
    }

    /**
     * 功能描述：公告详情查看
     * @param id
     * @return
     */
    @ApiOperation("公告详情查看")
    @GetMapping("/annc/{id}")
    public RestResult getAnnc(@PathVariable Integer id){
        Announcement annc = announcementService.getAnnc(id);
        if (annc == null) {
            throw new CustomizeException(CustomizeErrorCode.ANNC_NOT_FOUND);
        }
        return ResultGenerator.getSuccessResult(annc);
    }
}
