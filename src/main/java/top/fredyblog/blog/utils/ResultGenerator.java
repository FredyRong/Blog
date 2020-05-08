package top.fredyblog.blog.utils;

import top.fredyblog.blog.constant.ResultCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.exception.ICustomizeErrorCode;
import top.fredyblog.blog.model.dto.RestResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fredy
 * @date 2020/5/5 18:45
 */
public class ResultGenerator extends RestResult {

    private static final String SUCCESS = "success";

    //成功
    public static RestResult getSuccessResult() {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS);
    }

    //成功，附带额外数据
    public static RestResult getSuccessResult(Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }

    //成功，自定义消息及数据
    public static RestResult getSuccessResult(String message, Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    //失败，附带消息
    public static RestResult getFailResult(String message) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    //失败，自定义消息及数据
    public static RestResult getFailResult(String message, Object data) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }

    //自定义创建
    public static RestResult getFreeResult(ResultCode code, String message, Object data) {
        return new RestResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    //根据自定义exception创建
    public static RestResult getExResult(ResultCode code, String message, CustomizeException ce) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ce.getCode());
        map.put("message", ce.getMessage());
        return new RestResult()
                .setCode(code)
                .setMessage(message)
                .setData(map);
    }
}
