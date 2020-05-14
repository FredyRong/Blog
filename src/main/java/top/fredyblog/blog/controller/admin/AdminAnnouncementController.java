package top.fredyblog.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Announcement;
import top.fredyblog.blog.model.entity.AnnouncementExample;
import top.fredyblog.blog.service.AnnouncementService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;

/**
 * 管理员：公告管理
 * @author Fredy
 * @date 2020/5/14 20:38
 */
@Api("管理员公告管理模块")
@RestController
@RequestMapping("/admin")
public class AdminAnnouncementController {
    @Resource
    private AnnouncementService announcementService;

    /**
     * 功能描述：公告分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("公告分页查询")
    @GetMapping("/anncs")
    public RestResult types(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andDelFlagEqualTo(false);
        announcementExample.setOrderByClause("create_time desc");
        return ResultGenerator.getSuccessResult(announcementService.page(announcementExample, pageNum, pageSize));
    }


    /**
     * 功能描述：公告搜索
     * @param pageNum
     * @param pageSize
     * @param anncTile
     * @param topFlag
     * @param published
     * @return
     */
    @ApiOperation("公告搜索")
    @PostMapping("/annc/search")
    public RestResult search(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String anncTile, Boolean topFlag, Boolean published){
        AnnouncementExample announcementExample = new AnnouncementExample();
        AnnouncementExample.Criteria criteria = announcementExample.createCriteria().andDelFlagEqualTo(false);
        announcementExample.setOrderByClause("create_time desc");
        if(StringUtils.isNotBlank(anncTile)){
            criteria.andAnncTitleLike("%" + anncTile + "%");
        }
        if(topFlag != null){
            criteria.andTopFlagEqualTo(topFlag);
        }
        if(published != null){
            criteria.andPublishedEqualTo(published);
        }
        return ResultGenerator.getSuccessResult(announcementService.page(announcementExample, pageNum, pageSize));
    }

    /**
     * 功能描述：公告单个查询
     * @param id
     * @return
     */
    @ApiOperation("公告单个查询")
    @GetMapping("/annc/{id}")
    public RestResult getAnnc(@PathVariable Integer id){
        Announcement announcement = announcementService.findOne(id);
        if (announcement == null){
            throw new CustomizeException(CustomizeErrorCode.ANNC_NOT_FOUND);
        }
        return ResultGenerator.getSuccessResult(announcement);
    }

    /**
     * 功能描述：提交公告信息
     * @param announcement
     * @return
     */
    @ApiOperation("提交公告信息")
    @PostMapping("/annc")
    public RestResult postAnnc(Announcement announcement){
        announcementService.saveAnnouncement(announcement);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除公告
     * @param id
     * @return
     */
    @ApiOperation("删除公告")
    @DeleteMapping("/annc/{id}")
    public RestResult deleteAnnc(@PathVariable Integer id){
        announcementService.deleteAnnouncement(id);
        return ResultGenerator.getSuccessResult();
    }
}
