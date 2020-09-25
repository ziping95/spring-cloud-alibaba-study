package com.wzp.module.gateway.manager;

import com.wzp.module.core.constant.UserConstant;
import com.wzp.module.gateway.entity.SecurityUser;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/22
 */
@Component
public class AccessDeniedFilter implements WebFilter {

    private final AntPathMatcher antpathmatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getRawPath();
        SecurityUser securityUser = exchange.getAttribute(UserConstant.SESSION_USER_INFO);
        if (securityUser == null) {
            return Mono.error(new AuthenticationCredentialsNotFoundException("用户未登录"));
        }

        // 如果时超级管理员则拥有全部权限
        if (securityUser.isSuperAdmin()) {
            return chain.filter(exchange);
        }

        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        boolean result = authorities.stream().anyMatch(auth -> antpathmatcher.match(auth.getAuthority(), url));


        return result ? chain.filter(exchange) : Mono.error(new AccessDeniedException("权限不足"));
    }
}
