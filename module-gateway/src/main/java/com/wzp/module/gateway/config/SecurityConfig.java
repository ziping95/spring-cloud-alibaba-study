package com.wzp.module.gateway.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;
import com.wzp.module.gateway.handler.LoginFailureHandler;
import com.wzp.module.gateway.handler.LoginSuccessHandler;
import com.wzp.module.gateway.handler.MyAccessDeniedHandler;
import com.wzp.module.gateway.handler.MyAuthenticationEntryPoint;
import com.wzp.module.gateway.manager.AccessDeniedFilter;
import com.wzp.module.gateway.manager.AuthorizationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    // 无权限访问被拒绝时的自定义处理器。如不自己处理，默认返回403错误
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    // 未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    // 登录失败时调用的自定义处理类
    @Resource
    private LoginFailureHandler loginFailureHandler;

    // 登录成功时调用的自定义处理类
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    // 自定义的鉴权服务，通过鉴权的才能继续访问某个请求
    @Resource
    private AuthorizationManager authorizationManager;

    @Resource
    private AccessDeniedFilter accessDeniedFilter;
    @Value("${security.white.list.file}")
    private String whiteListFile;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http.addFilterAfter(accessDeniedFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        // 异常处理器
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint);

        // 设置处理器
        http.formLogin().loginPage("/loginByUser")
                .authenticationFailureHandler(loginFailureHandler)
                .authenticationSuccessHandler(loginSuccessHandler);

        String json = FileUtil.readString(ClassUtil.getClassPath() + whiteListFile, "utf-8");
        List<String> list = JSONUtil.parseArray(json).toList(String.class);
        String[] whiteList = list.toArray(new String[0]);

        http.authorizeExchange()
                // 无需进行权限过滤的请求路径
                .pathMatchers(whiteList).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

        return http.build();
    }


}
