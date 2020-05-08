package top.fredyblog.blog.constant;

/**
 * Redis相关常量
 * @author Fredy
 * @date 2020/5/7 15:04
 */
public enum RedisConstant {
    COMMENT_KEY("comment"),
    VIEW_KEY("view"),
    LIKE_KEY("like")
    ;
    private String desc;

    RedisConstant(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
