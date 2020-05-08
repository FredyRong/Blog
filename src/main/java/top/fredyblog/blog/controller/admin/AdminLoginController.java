package top.fredyblog.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fredyblog.blog.constant.UserRole;
import top.fredyblog.blog.model.dto.LoginDTO;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.service.UserService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员：登录管理
 * @author Fredy
 * @date 2020/5/5 16:10
 */
@Api("管理员登陆管理模块")
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Resource
    private UserService userService;

    /**
     * 管理员登录
     * @param loginDTO
     * @param session
     * @param request
     * @return
     */
    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public RestResult login(LoginDTO loginDTO, HttpSession session, HttpServletRequest request){
        loginDTO.setUserRole(UserRole.USER_ROLE_SUPER_ADMIN.getCode());
        User user = userService.checkUser(loginDTO);
        userService.updateLoginInfo(user, request.getHeader("X-Real-IP"));
        user.setPassword(null);
        session.setAttribute("user", user);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 管理员登出
     * @param session
     * @return
     */
    @ApiOperation("管理员登出")
    @GetMapping("/logout")
    public RestResult logout(HttpSession session){
        session.removeAttribute("user");
        return ResultGenerator.getSuccessResult();
    }

}
