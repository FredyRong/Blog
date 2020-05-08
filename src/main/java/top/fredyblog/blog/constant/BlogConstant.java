package top.fredyblog.blog.constant;

/**
 * 项目常量
 * @author Fredy
 * @date 2020/5/5 17:49
 */
public enum BlogConstant implements BlogDictionary{
    DEFAULT_HEAD_MALE_PORTRAIT("/images/male.png", "男性头像"),
    DEFAULT_HEAD_FEMALE_PORTRAIT("/images/male.png", "女性头像"),
    ;
    private String code;
    private String desc;

    BlogConstant(String code, String desc){
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
