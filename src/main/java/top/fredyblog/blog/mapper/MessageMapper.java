package top.fredyblog.blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.fredyblog.blog.model.entity.Message;
import top.fredyblog.blog.model.entity.MessageExample;

public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBsWithRowbounds(MessageExample example, RowBounds rowBounds);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExampleWithRowbounds(MessageExample example, RowBounds rowBounds);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer messageId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}