package com.wzp.module.user.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzp.module.core.pojo.User;

import javax.annotation.Resource;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */
public interface UserService extends IService<User> {

    User findUserByCondition(User user);

}
