package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.entity.Announcement;
import top.fredyblog.blog.model.entity.AnnouncementExample;

/**
 * 公告管理服务层接口
 * @author Fredy
 * @date 2020/5/11 19:03
 */
public interface AnnouncementService {
    /**
     * 功能描述：分页查询公告信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Announcement> page(Integer pageNum, Integer pageSize);

    /**
     * 功能描述：公告详情查看
     * @param id
     * @return
     */
    Announcement getAnnc(Integer id);

    /**
     * 功能描述：分页查询公告信息（自定义查询条件）
     * @param announcementExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Announcement> page(AnnouncementExample announcementExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述：主键查询
     * @param id
     * @return
     */
    Announcement findOne(Integer id);

    /**
     * 功能描述：删除公告信息
     * @param id
     */
    void deleteAnnouncement(Integer id);

    /**
     * 功能描述：更新公告信息
     * @param announcement
     */
    void saveAnnouncement(Announcement announcement);
}
