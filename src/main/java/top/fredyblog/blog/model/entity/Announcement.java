package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Announcement implements Serializable {
    //主键
    private Integer anncId;
    //公告标题
    private String anncTitle;
    //公告描述
    private String anncDesc;
    //公告内容
    private String anncContent;
    //是否置顶
    private Boolean topFlag;
    //是否发布：0-草稿 1-公布
    private Boolean published;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志：0-未删除，1-删除
    private Boolean delFlag;
    //删除时间
    private LocalDateTime delTime;

    private static final long serialVersionUID = 1L;
}