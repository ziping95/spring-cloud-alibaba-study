package com.wzp.module.gateway.service;


import cn.hutool.core.bean.BeanUtil;
import com.wzp.module.core.dto.UserDto;
import com.wzp.module.gateway.entity.SecurityUser;
import com.wzp.module.gateway.feign.UserFeign;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements ReactiveUserDetailsService {


    @Resource
    private UserFeign userFeign;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserDto userDto;
        userDto = userFeign.getUserByLoginId(username);
        if (userDto.isNull()) {
            return Mono.error(new UsernameNotFoundException("用户名不存在"));
        }
        SecurityUser securityUser = new SecurityUser();
        BeanUtil.copyProperties(userDto,securityUser);
        return Mono.just(securityUser);
    }
}
