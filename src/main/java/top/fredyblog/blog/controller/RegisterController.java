package top.fredyblog.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.model.dto.RegisterDTO;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.service.UserService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Fredy
 * @date 2020/5/16 13:59
 */
@Api("注册控制层")
@RestController
@RequestMapping("/user")
@Log4j2
public class RegisterController {
    @Resource
    private UserService userService;

    /**
     * 功能描述：用户注册
     * @param registerDTO
     * @param request
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public RestResult register(RegisterDTO registerDTO, HttpServletRequest request){
        userService.register(registerDTO, request.getRemoteAddr());
        return ResultGenerator.getSuccessResult();
    }
}
