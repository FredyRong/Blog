package top.fredyblog.blog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    //用户id
    private Integer userId;
    //用户登录名
    private String username;
    //昵称
    private String nickname;
    //登录密码
    private String password;
    //性别：0-女 1-男
    private Boolean userGender;
    //角色类型：1-超级管理员；2-普通管理员; 3-VIP用户；4-普通用户
    private String userRole;
    //用户状态：0-封禁；1-正常
    private String userStatus;
    //联系方式
    private String telephone;
    //邮箱
    private String email;
    //用户头像
    private String headPortrait;
    //注册时间
    private LocalDateTime registerTime;
    //最后登录时间
    private LocalDateTime lastLoginTime;
    //最后登录ip
    private String lastLoginIp;
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