package top.fredyblog.blog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    SERVER_ERROR(1001, "服务器错误"),
    REQUEST_NOT_EXIST(1002, "请求不存在"),
    UNKNOWN_ERROR(1003, "未知错误？你发现了新大陆"),
    BLOG_NOT_FOUND(2001, "博客不存在"),
    BLOG_DELETE_FAILED(2002, "博客删除失败"),
    BLOG_UPDATE_FAILED(2003, "博客更新失败"),
    BLOG_INSERT_FAILED(2004, "博客插入失败"),
    BLOG_TAG_NOT_FOUND(2005, "博客-标签不存在"),
    BLOG_TAG_DELETE_FAILED(2006, "博客-标签删除失败"),
    BLOG_TAG_UPDATE_FAILED(2007, "博客-标签更新失败"),
    BLOG_TAG_INSERT_FAILED(2008, "博客-标签插入失败"),
    TAG_NOT_FOUND(3001, "标签不存在"),
    TAG_EXISTED(3002, "标签已存在"),
    TAG_DELETE_FAILED(3003, "标签删除失败"),
    TAG_UPDATE_FAILED(3004, "标签更新失败"),
    TAG_INSERT_FAILED(3005, "标签插入失败"),
    TYPE_NOT_FOUND(4001, "类型不存在"),
    TYPE_EXISTED(4002, "类型已存在"),
    TYPE_DELETE_FAILED(4003, "类型删除失败"),
    TYPE_UPDATE_FAILED(4004, "类型更新失败"),
    TYPE_INSERT_FAILED(4005, "类型插入失败"),
    USER_NOT_FOUND(5001, "用户不存在"),
    USER_EXISTED(5002, "用户已存在"),
    USER_DELETE_FAILED(5003, "用户删除失败"),
    USER_UPDATE_FAILED(5004, "用户更新失败"),
    USER_INSERT_FAILED(5005, "用户插入失败"),
    MESSAGE_NOT_FOUND(6001, "留言不存在"),
    MESSAGE_EXISTED(6002, "留言已存在"),
    MESSAGE_DELETE_FAILED(6003, "留言删除失败"),
    MESSAGE_UPDATE_FAILED(6004, "留言更新失败"),
    MESSAGE_INSERT_FAILED(6005, "留言插入失败"),
    MAIL_ILLEGAL(7001, "邮箱不合法")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
