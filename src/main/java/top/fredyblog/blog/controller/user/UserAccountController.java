package top.fredyblog.blog.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.LoginDTO;
import top.fredyblog.blog.model.dto.RegisterDTO;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.UserService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户管理控制层
 * @author Fredy
 * @date 2020/5/16 16:02
 */
@Api("用户管理控制层")
@RestController
@RequestMapping("/user")
public class UserAccountController {
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

    /**
     * 功能描述：用户登录
     * @param loginDTO
     * @param session
     * @param request
     * @return
     */
    @ApiOperation("用户登录")
    @GetMapping("/login")
    public RestResult login(LoginDTO loginDTO, HttpSession session, HttpServletRequest request){
        User user = userService.checkUser(loginDTO);
        if(user != null){
            userService.updateLoginInfo(user, request.getHeader("X-Real-IP"));
            user.setPassword(null);
            session.setAttribute("user", user);
            String path = StringUtils.isBlank(loginDTO.getPath()) ? "/" : loginDTO.getPath();
            return ResultGenerator.getSuccessResult(path);
        }else{
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * 功能描述：注销功能
     * @param session
     * @param request
     * @return
     */
    @ApiOperation("注销功能")
    @GetMapping("/logout")
    public RestResult logout(HttpSession session, HttpServletRequest request){
        session.removeAttribute("user");
        String referer = request.getHeader("referer");
        return ResultGenerator.getSuccessResult(referer);
    }
}
