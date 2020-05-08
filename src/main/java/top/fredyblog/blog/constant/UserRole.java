package top.fredyblog.blog.constant;

import lombok.Data;

/**
 * 用户角色类型
 * @author Fredy
 * @date 2020/5/5 17:33
 */
public enum UserRole implements BlogDictionary{
    USER_ROLE_SUPER_ADMIN("1", "超级管理员"),
    USER_ROLE_GENERAL_ADMIN("2", "普通管理员"),
    USER_ROLE_VIP_USER("3", "VIP用户"),
    USER_ROLE_GENERAL_USER("4", "普通用户"),
    ;

    private String code;
    private String desc;

    UserRole(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getDesc() {
        return desc;
    }
}
