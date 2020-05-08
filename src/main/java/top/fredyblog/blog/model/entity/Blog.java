package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Blog implements Serializable {
    //主键
    private Integer blogId;
    //博主id，对应用户id
    private Integer userId;
    //博客类型
    private Integer typeId;
    //博客标志：1-原创，2-转载，3-翻译
    private String blogFlag;
    //博客标签字符串，格式,1,2,3,4,5
    private String tagIdStr;
    //首图地址
    private String firstPictureAddr;
    //博客标题
    private String blogTitle;
    //博客描述
    private String blogDesc;
    //博客内容
    private String blogContent;
    //是否推荐
    private Boolean recommend;
    //转载声明是否开启
    private Boolean shareFlag;
    //是否发布：0-草稿，1-发布
    private Boolean published;
    //是否开启赞赏
    private Boolean appreciationFlag;
    //是否开启评论
    private Boolean commentabled;
    //博客浏览次数
    private Integer blogViews;
    //博客评论次数
    private Integer blogComments;
    //博客点赞次数
    private Integer blogLikes;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标识
    private Boolean delFlag;
    //删除时间
    private LocalDateTime delTime;

    private static final long serialVersionUID = 1L;
}