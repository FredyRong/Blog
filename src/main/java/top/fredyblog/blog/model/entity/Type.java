package top.fredyblog.blog.model.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Type implements Serializable {
    //主键
    private Integer typeId;
    //类型名
    @NotBlank(message = "类型名不能为空")
    private String typeName;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志：0-未删除 1-删除
    private Boolean delFlag;
    //删除时间
    private LocalDateTime delTime;

    private static final long serialVersionUID = 1L;
}