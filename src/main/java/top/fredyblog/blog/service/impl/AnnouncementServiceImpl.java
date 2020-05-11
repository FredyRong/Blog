package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.AnnouncementMapper;
import top.fredyblog.blog.model.entity.Announcement;
import top.fredyblog.blog.model.entity.AnnouncementExample;
import top.fredyblog.blog.service.AnnouncementService;
import top.fredyblog.blog.utils.MarkdownUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告管理服务层实现
 * @author Fredy
 * @date 2020/5/11 19:06
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Resource
    private AnnouncementMapper announcementMapper;

    /**
     * 功能描述：分页查询公告信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Announcement> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andDelFlagEqualTo(false).andPublishedEqualTo(true);
        List<Announcement> list = announcementMapper.selectByExample(announcementExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述：公告详情查看
     * @param id
     * @return
     */
    @Override
    public Announcement getAnnc(Integer id) {
        Announcement announcement = announcementMapper.selectByPrimaryKey(id);
        announcement.setAnncContent(MarkdownUtils.markdownToHtmlExtensions(announcement.getAnncContent()));
        return announcement;
    }

    /**
     * 功能描述：分页查询公告信息（自定义查询条件）
     * @param announcementExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Announcement> page(AnnouncementExample announcementExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> list = announcementMapper.selectByExample(announcementExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述：主键查询
     * @param id
     * @return
     */
    @Override
    public Announcement findOne(Integer id) {
        return announcementMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述：删除公告信息
     * @param id
     */
    @Override
    public void deleteAnnouncement(Integer id) {
        Announcement announcement = new Announcement();
        announcement.setAnncId(id);
        announcement.setDelFlag(true);
        announcement.setDelTime(LocalDateTime.now());
        int n = announcementMapper.updateByPrimaryKeySelective(announcement);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.ANNC_DELETE_FAILED);
        }
    }

    /**
     * 功能描述：更新公告信息
     * @param announcement
     */
    @Override
    public void saveAnnouncement(Announcement announcement) {
        LocalDateTime now = LocalDateTime.now();
        announcement.setUpdateTime(now);
        if (announcement.getTopFlag() == null) {
            announcement.setTopFlag(false);
        }
        if (announcement.getAnncId() == null) {
            announcement.setCreateTime(now);
            announcement.setDelFlag(false);
            int n = announcementMapper.insertSelective(announcement);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.ANNC_INSERT_FAILED);
            }
        } else {
            int n = announcementMapper.updateByPrimaryKeySelective(announcement);
            if(n != 1){
                throw new CustomizeException(CustomizeErrorCode.ANNC_UPDATE_FAILED);
            }
        }
    }
}
