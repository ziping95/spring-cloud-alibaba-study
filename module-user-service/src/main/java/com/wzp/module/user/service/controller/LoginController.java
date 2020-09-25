package com.wzp.module.user.service.controller;

import com.wzp.module.core.result.ResultDataModel;
import com.wzp.module.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RefreshScope
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${server.port}")
    private String port;


    @GetMapping("/open/test-a")
    public ResultDataModel test_A() {
        return ResultDataModel.handleSuccessResult("访问成功，不需要登录，任意权限均可访问");
    }

    @GetMapping("/test-b")
    public ResultDataModel test_B() {
        return ResultDataModel.handleSuccessResult("访问成功，仅需要登录即可访问");
    }

    @GetMapping("/admin/test-c")
    public ResultDataModel test_C() {
        return ResultDataModel.handleSuccessResult("访问成功，需要管理员权限才可访问");
    }

    @GetMapping("/super/test-d")
    public ResultDataModel test_D() {
        return ResultDataModel.handleSuccessResult("访问成功，需要超级管理员权限才可访问");
    }
}
