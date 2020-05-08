package top.fredyblog.blog.model.dto;

import lombok.Data;

/**
 * 登录提交信息传输类
 * @author Fredy
 * @date 2020/5/5 17:14
 */
@Data
public class LoginDTO {
    //用户名
    private String username;
    //密码
    private String password;
    //跳转路径
    private String path;
    //用户角色
    private String userRole;
}
