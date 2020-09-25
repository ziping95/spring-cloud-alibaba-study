package com.wzp.module.gateway.handler;

import cn.hutool.json.JSONUtil;
import com.wzp.module.core.result.ErrorCodeEnum;
import com.wzp.module.core.result.ResultDataModel;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 权限不足处理类
 */
@Component
public class MyAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add("Content-Type","application/json; charset=UTF-8");
        byte[] dataBytes = JSONUtil.toJsonStr(ResultDataModel.handleResult(ErrorCodeEnum.ACCESS_DENIED)).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(wrap));
    }
}
