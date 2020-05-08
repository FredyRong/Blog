package top.fredyblog.blog.model.vo;

import lombok.Data;
import top.fredyblog.blog.model.entity.Blog;
import top.fredyblog.blog.model.entity.BlogTag;

import java.util.List;

/**
 * 博客信息vo
 * @author Fredy
 * @date 2020/5/6 22:58
 */
@Data
public class BlogVo extends Blog {
    //博客作者昵称
    private String nickname;
    //博客作者头像
    private String headPortrait;
    //当前登录用户是否点赞过
    private boolean flag;
    //博客标签列表
    private List<BlogTag> tags;
}
