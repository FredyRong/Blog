package top.fredyblog.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.fredyblog.blog.constant.BlogConstant;
import top.fredyblog.blog.constant.UserRole;
import top.fredyblog.blog.constant.UserStatus;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.mapper.BlogMapper;
import top.fredyblog.blog.mapper.BlogTagMapper;
import top.fredyblog.blog.mapper.UserMapper;
import top.fredyblog.blog.model.dto.LoginDTO;
import top.fredyblog.blog.model.dto.RegisterDTO;
import top.fredyblog.blog.model.entity.*;
import top.fredyblog.blog.service.UserService;
import top.fredyblog.blog.utils.MD5Utils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户管理服务层实现
 * @author Fredy
 * @date 2020/5/5 17:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogTagMapper blogTagMapper;

    /**
     * 用户注册
     * @param registerDTO 注册提交的信息
     * @param remoteIp 客户机IP
     */
    @Override
    public void register(RegisterDTO registerDTO, String remoteIp) {
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().username(registerDTO.getUsername())
                .password(MD5Utils.code(registerDTO.getPassword()))
                .nickname(registerDTO.getNickname())
                .userGender(registerDTO.getGender())
                .email(registerDTO.getEmail())
                .telephone(registerDTO.getTelephone())
                .userRole(UserRole.USER_ROLE_GENERAL_USER.getCode())
                .headPortrait(registerDTO.getGender() ? BlogConstant.DEFAULT_HEAD_MALE_PORTRAIT.getCode() : BlogConstant.DEFAULT_HEAD_FEMALE_PORTRAIT.getCode())
                .lastLoginIp(remoteIp)
                .registerTime(now)
                .lastLoginTime(now)
                .createTime(now)
                .updateTime(now)
                .delFlag(false)
                .build();
        int n = userMapper.insert(user);
        if(n != 1){
            throw new CustomizeException(CustomizeErrorCode.USER_REGISTER_FAILED);
        }
    }

    /**
     * 登录校验
     * @param loginDTO 登录提交的信息
     * @return
     */
    @Override
    public User checkUser(LoginDTO loginDTO) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria().andUsernameEqualTo(loginDTO.getUsername())
                .andPasswordEqualTo(MD5Utils.code(loginDTO.getPassword()))
                .andUserStatusEqualTo(UserStatus.USER_STATUS_NORMAL.getCode())
                .andDelFlagEqualTo(false);
        if(StringUtils.equalsAny(loginDTO.getUserRole(), UserRole.USER_ROLE_GENERAL_ADMIN.getCode(), UserRole.USER_ROLE_SUPER_ADMIN.getCode())){
            criteria.andUserRoleIn(Arrays.asList(UserRole.USER_ROLE_SUPER_ADMIN.getCode(), UserRole.USER_ROLE_GENERAL_ADMIN.getCode()));
        }
        List<User> users = userMapper.selectByExample(userExample);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    /**
     * 更新登录信息
     * @param user 用户
     * @param lastLoginIp 最后登录IP
     */
    @Override
    public void updateLoginInfo(User user, String lastLoginIp) {
        User record = User.builder().userId(user.getUserId())
                .lastLoginIp(lastLoginIp)
                .lastLoginTime(LocalDateTime.now()).build();
        int n = userMapper.updateByPrimaryKeySelective(record);
        if(n != 0){
            throw new CustomizeException(CustomizeErrorCode.USER_UPDATE_FAILED);
        }
    }

    /**
     * 获取用户信息
     * @param userExample 查询模板
     * @param pageNum 所处页码
     * @param pageSize 一页展示数目
     * @return 分页信息
     */
    @Override
    public PageInfo<User> getAllUser(UserExample userExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectByExample(userExample);
        return PageInfo.of(users);
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        //删除用户信息
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().userId(id)
                .delFlag(true)
                .delTime(now).build();
        int n1 = userMapper.updateByPrimaryKeySelective(user);
        if(n1 != 0){
            throw new CustomizeException(CustomizeErrorCode.USER_DELETE_FAILED);
        }
        //删除用户的博客信息
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andUserIdEqualTo(id).andDelFlagEqualTo(false);
        List<Blog> blogList = blogMapper.selectByExample(blogExample);
        BlogTagExample blogTagExample = new BlogTagExample();
        for (Blog blog : blogList) {
            blog.setDelFlag(true);
            blog.setDelTime(now);
            int n2 = blogMapper.updateByExampleSelective(blog, blogExample);
            if(n2 != 0){
                throw new CustomizeException(CustomizeErrorCode.BLOG_UPDATE_FAILED);
            }
            //删除用户博客-标签关系信息
            blogTagExample.clear();
            blogTagExample.createCriteria().andBlogIdEqualTo(blog.getBlogId());
            int n3 = blogTagMapper.deleteByExample(blogTagExample);
            if(n3 != 0){
                throw new CustomizeException(CustomizeErrorCode.BLOG_TAG_DELETE_FAILED);
            }
        }
    }

    /**
     * 封禁用户
     * @param id 用户id
     */
    @Override
    public void banUser(Integer id) {
        User user = User.builder().userId(id)
                .userStatus(UserStatus.USER_STATUS_BAN.getCode())
                .updateTime(LocalDateTime.now())
                .build();
        int n = userMapper.updateByPrimaryKeySelective(user);
        if(n != 0){
            throw new CustomizeException(CustomizeErrorCode.USER_UPDATE_FAILED);
        }
    }

    /**
     * 解禁用户
     * @param id 用户id
     */
    @Override
    public void unblockUser(Integer id) {
        User user = User.builder().userId(id)
                .userStatus(UserStatus.USER_STATUS_NORMAL.getCode())
                .updateTime(LocalDateTime.now())
                .build();
        int n = userMapper.updateByPrimaryKeySelective(user);
        if(n != 0){
            throw new CustomizeException(CustomizeErrorCode.USER_UPDATE_FAILED);
        }
    }
}
