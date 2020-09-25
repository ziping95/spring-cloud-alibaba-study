package com.wzp.module.gateway.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.wzp.module.core.util.UserCacheUtil;
import com.wzp.module.gateway.entity.SecurityUser;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description : 只校验是否有token，且token是否过期
 * @Author : wzp
 * @Date : 2020/8/20
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    private UserCacheUtil userCacheUtil;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        List<HttpCookie> cookie = cookies.get("token");
        if (CollUtil.isEmpty(cookie)) {
            return Mono.just(new AuthorizationDecision(false));
        }
        String json = (String) userCacheUtil.getSecurityUser(cookie.get(0).getValue());

        SecurityUser securityUser = JSONUtil.toBean(json, SecurityUser.class);

        return Mono.just(new AuthorizationDecision(Objects.nonNull(securityUser)));
    }
}
