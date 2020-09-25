package com.wzp.module.gateway.handler;

import cn.hutool.json.JSONUtil;
import com.wzp.module.core.result.ResultDataModel;
import com.wzp.module.core.util.UserCacheUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 认证成功处理类
 */
@Component
public class LoginSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Resource
    private UserCacheUtil userCacheUtil;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication){
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");

        String json = JSONUtil.toJsonStr(authentication.getPrincipal());
        // 缓存至redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        userCacheUtil.putSecurityUser(token,json);
        response.addCookie(ResponseCookie.from("token",token).build());
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(ResultDataModel.handleSuccessResult("登录成功")).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
