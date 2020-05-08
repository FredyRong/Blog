package top.fredyblog.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.fredyblog.blog.model.entity.Announcement;
import top.fredyblog.blog.model.entity.AnnouncementExample;

public interface AnnouncementMapper {
    long countByExample(AnnouncementExample example);

    int deleteByExample(AnnouncementExample example);

    int deleteByPrimaryKey(Integer anncId);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    List<Announcement> selectByExampleWithBLOBsWithRowbounds(AnnouncementExample example, RowBounds rowBounds);

    List<Announcement> selectByExampleWithBLOBs(AnnouncementExample example);

    List<Announcement> selectByExampleWithRowbounds(AnnouncementExample example, RowBounds rowBounds);

    List<Announcement> selectByExample(AnnouncementExample example);

    Announcement selectByPrimaryKey(Integer anncId);

    int updateByExampleSelective(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExampleWithBLOBs(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExample(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKeyWithBLOBs(Announcement record);

    int updateByPrimaryKey(Announcement record);
}