package com.wzp.module.user.service.fegin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzp.module.core.dto.UserDto;
import com.wzp.module.core.pojo.User;
import com.wzp.module.user.service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/14
 */

@RestController
@RequestMapping("/feign/user")
public class UserFeignService {

    @Resource
    private UserService userService;

    @GetMapping("/getUserByLoginId")
    public UserDto getUserByLoginId(@RequestParam("loginId") String loginId) {
        User user = userService.findUserByCondition(new User().setLoginId(loginId));
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user,userDto);
        return userDto;
    }

    @GetMapping("/getUserByPhone")
    public UserDto getUserByPhone(@RequestParam("phone") Long phone) {
        User user = userService.findUserByCondition(new User().setPhone(phone));
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user,userDto);
        return userDto;
    }

    @GetMapping("/getUserByMail")
    public UserDto getUserByMail(@RequestParam("mail") String mail) {
        User user = userService.findUserByCondition(new User().setMail(mail));
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user,userDto);
        return userDto;
    }



}
