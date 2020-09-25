package com.wzp.module.user.service;

import com.wzp.module.core.pojo.User;
import com.wzp.module.user.service.mapper.UserMapper;
import com.wzp.module.user.service.service.UserService;
import javafx.print.PageOrientation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

//@SpringBootTest
class ModuleUserServiceApplicationTests {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;


    @Test
    public void ter() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        System.out.println(userMapper.findUserByIds(ids));
    }

    @Test
    public void ter1() {

    }




}
