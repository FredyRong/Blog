package top.fredyblog.blog.service;

import com.github.pagehelper.PageInfo;
import top.fredyblog.blog.model.dto.LoginDTO;
import top.fredyblog.blog.model.dto.RegisterDTO;
import top.fredyblog.blog.model.entity.User;
import top.fredyblog.blog.model.entity.UserExample;

/**
 * 用户管理服务层接口
 * @author Fredy
 * @date 2020/5/5 17:08
 */
public interface UserService {

    /**
     * 功能描述：注册用户
     * @param registerDTO
     * @param remoteIp
     */
    void register(RegisterDTO registerDTO, String remoteIp);

    /**
     * 功能描述：登陆校验
     * @param loginDTO
     * @return
     */
    User checkUser(LoginDTO loginDTO);

    /**
     * 功能描述：登录更新用户信息
     * @param user
     * @param lastLoginIp
     */
    void updateLoginInfo(User user,String lastLoginIp);

    /**
     * 功能描述：或许用户信息
     * @param userExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> getAllUser(UserExample userExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述：删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 功能描述：封禁用户
     * @param id
     */
    void banUser(Integer id);

    /**
     * 功能描述：解封用户
     * @param id
     */
    void unblockUser(Integer id);
}
