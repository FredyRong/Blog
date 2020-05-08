package top.fredyblog.blog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogTag implements Serializable {
    //主键
    private Integer id;
    //博客id
    private Integer blogId;
    //标签id
    private Integer tagId;

    private static final long serialVersionUID = 1L;
}