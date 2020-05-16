package top.fredyblog.blog.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import top.fredyblog.blog.model.entity.BlogTag;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 博客搜索类
 * @author Fredy
 * @date 2020/5/16 17:57
 */
@Document(indexName = "fredyblog", //索引名
        type = "blog", //类型
        shards = 1, //默认索引分区数
        replicas = 0, //每个分区的备份数
        refreshInterval = "-1" //刷新间隔
)
@Data
public class EsBlogDo {
    //主键
    @Id
    private Integer blogId;
    //博主id，对应用户id
    private Integer userId;
    //博客标志：1-原创，2-转载，3-翻译
    private String blogFlag;
    //博客描述
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String blogDesc;
    //是否推荐
    private Boolean recommend;
    //转载声明是否开启
    private Boolean shareFlag;
    //博客类型
    private Integer typeId;
    //博客标签字符串，格式：-1-2-3-4-5-
    private String tagIdStr;
    //首图地址
    private String firstPictureAddr;
    //是否发布：0-草稿 1-发布
    private Boolean published;
    //博客标题
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String blogTitle;
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
    //博客内容
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String blogContent;
    //博客作者昵称
    private String nickname;
    //博客作者头像
    private String headPortrait;
    //当前登录用户是否点赞过
    private Boolean flag;
    //博客标签列表
    private List<BlogTag> tags;
}
