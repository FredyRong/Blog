package top.fredyblog.blog.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fredyblog.blog.constant.ResultCode;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * 统一异常处理
 * @author Fredy
 * @date 2020/5/8 11:43
 */
@RestController
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * 异常处理
     * @param request
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public RestResult exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        log.error("Request URL: {}, ExceptionMessage: {}", request.getRequestURL(), e.getMessage());
        HttpStatus status = getStatus(request);
        if(e instanceof CustomizeException){
            CustomizeException ce = (CustomizeException)e;
            return ResultGenerator.getExResult(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), ce);
        }else if(status.is5xxServerError() || e instanceof ParseException){
            return ResultGenerator.getExResult(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), new CustomizeException(CustomizeErrorCode.SERVER_ERROR));
        }else if(status.is4xxClientError()){
            return ResultGenerator.getExResult(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), new CustomizeException(CustomizeErrorCode.REQUEST_NOT_EXIST));
        }
        return ResultGenerator.getExResult(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage(), new CustomizeException(CustomizeErrorCode.UNKNOWN_ERROR));
    }

    /**
     * 获取错误状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}
