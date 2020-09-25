package com.wzp.module.user.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzp.module.core.pojo.User;
import com.wzp.module.user.service.mapper.UserMapper;
import com.wzp.module.user.service.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByCondition(User user) {
        return userMapper.findUserByCondition(user);
    }
}
