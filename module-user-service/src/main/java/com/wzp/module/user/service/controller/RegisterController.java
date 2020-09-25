package com.wzp.module.user.service.controller;

import com.wzp.module.core.param.UserParam;
import com.wzp.module.core.result.ResultDataModel;
import com.wzp.module.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/13
 */
@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserService userService;

    @PutMapping("/register")
    public ResultDataModel register(@RequestBody UserParam userParam) {
        return ResultDataModel.handleSuccessResult(userService.list());
    }
}
