package top.fredyblog.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注册功能数据传输类
 * @author Fredy
 * @date 2020/5/5 17:14
 */
@Data
public class RegisterDTO implements Serializable {
    //用户登录名
    @NotNull
    private String username;
    //昵称
    @NotNull
    private String nickname;
    //性别
    @NotNull
    private Boolean gender;
    //登录密码
    @NotNull
    private String password;
    //联系方式
    private String telephone;
    //邮箱
    private String email;

    private static final long serialVersionUID = 1L;
}
