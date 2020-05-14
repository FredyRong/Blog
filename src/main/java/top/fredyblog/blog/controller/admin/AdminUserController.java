package top.fredyblog.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.constant.UserRole;
import top.fredyblog.blog.constant.UserStatus;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.UserExample;
import top.fredyblog.blog.service.UserService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员：用户管理
 * @author Fredy
 * @date 2020/5/9 23:01
 */
@Api("管理员用户管理模块")
@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminUserController {
    //用户角色对应map
    private static Set<UserRole> userRoleSet = new HashSet<>();
    //用户状态对应map
    private static Set<UserStatus> userStatusSet = new HashSet<>();
    static {
        userRoleSet.add(UserRole.USER_ROLE_SUPER_ADMIN);
        userRoleSet.add(UserRole.USER_ROLE_GENERAL_ADMIN);
        userRoleSet.add(UserRole.USER_ROLE_VIP_USER);
        userRoleSet.add(UserRole.USER_ROLE_GENERAL_USER);
        userStatusSet.add(UserStatus.USER_STATUS_NORMAL);
        userStatusSet.add(UserStatus.USER_STATUS_BAN);
    }
    @Resource
    private UserService userService;

    /**
     * 功能描述：获取用户分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("获取用户分页信息")
    @GetMapping("/users")
    public RestResult users(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("create_time");
        userExample.createCriteria().andDelFlagEqualTo(false);
        return ResultGenerator.getSuccessResult(userService.getAllUser(userExample, pageNum, pageSize));
    }

    /**
     * 功能描述：获取用户角色集合
     * @return
     */
    @ApiOperation("获取用户角色集合")
    @GetMapping("/userRoleSet")
    public RestResult userRoleSet(){
        return ResultGenerator.getSuccessResult(userRoleSet);
    }

    /**
     * 功能描述：获取用户状态集合
     * @return
     */
    @ApiOperation("获取用户状态集合")
    @GetMapping("/userStatusSet")
    public RestResult userStatusSet(){
        return ResultGenerator.getSuccessResult(userStatusSet);
    }

    /**
     * 功能描述：封禁用户
     * @param id
     * @return
     */
    @ApiOperation("封禁用户")
    @PutMapping("/users/ban/{id}")
    public RestResult banUser(@PathVariable Integer id){
        userService.banUser(id);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：解禁用户
     * @param id
     * @return
     */
    @ApiOperation("封禁用户")
    @PutMapping("/users/unBlock/{id}")
    public RestResult unBlockUser(@PathVariable Integer id){
        userService.unblockUser(id);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除用户
     * @param id
     * @return
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/users/{id}")
    public RestResult deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResultGenerator.getSuccessResult();
    }
}
