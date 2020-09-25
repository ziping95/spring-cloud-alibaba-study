package com.wzp.module.user.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "com.wzp.module.user.service.mapper")
@EnableTransactionManagement // 开启事务
@EnableFeignClients
@ComponentScan("com.wzp")
@SpringCloudApplication
public class ModuleUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleUserServiceApplication.class, args);
    }

}
