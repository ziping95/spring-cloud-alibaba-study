package com.wzp.module.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.wzp")
@EnableFeignClients
@SpringCloudApplication
public class ModuleGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleGatewayApplication.class, args);
    }

}
