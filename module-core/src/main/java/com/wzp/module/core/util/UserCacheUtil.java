package com.wzp.module.core.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class UserCacheUtil {

    private static final String REDIS_FORMAT = "user:token:%s";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Object getSecurityUser(String token) {
        return redisTemplate.opsForValue().get(String.format(REDIS_FORMAT, token));
    }

    public void putSecurityUser(String token, Object securityUser) {
        redisTemplate.opsForValue().set(String.format(REDIS_FORMAT,token),securityUser,7200, TimeUnit.SECONDS);
    }

}
