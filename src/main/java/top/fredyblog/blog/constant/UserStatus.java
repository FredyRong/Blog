package top.fredyblog.blog.constant;

/**
 * 用户状态
 * @author Fredy
 * @date 2020/5/5 17:41
 */
public enum  UserStatus implements BlogDictionary{
    USER_STATUS_BAN("0", "封禁"),
    USER_STATUS_NORMAL("1", "正常"),
    ;

    private String code;
    private String desc;

    UserStatus(String code, String desc){
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
