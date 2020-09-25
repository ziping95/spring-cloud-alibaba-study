package com.wzp.module.gateway.handler;

import cn.hutool.json.JSONUtil;
import com.wzp.module.core.result.ErrorCodeEnum;
import com.wzp.module.core.result.ResultDataModel;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 未登录处理类
 */
@Component
public class MyAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json; charset=UTF-8");

        byte[] dataBytes = JSONUtil.toJsonStr(ResultDataModel.handleResult(ErrorCodeEnum.NOT_LOG_IN)).getBytes(StandardCharsets.UTF_8);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
