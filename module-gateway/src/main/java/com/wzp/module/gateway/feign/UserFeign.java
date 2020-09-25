package com.wzp.module.gateway.feign;

import com.wzp.module.core.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "module-user-service",path = "/feign/user")
public interface UserFeign {

    @GetMapping("/getUserByLoginId")
    UserDto getUserByLoginId(@RequestParam("loginId") String loginId);

    @GetMapping("/getUserByPhone")
    UserDto getUserByPhone(@RequestParam("phone") Long phone);

    @GetMapping("/getUserByMail")
    UserDto getUserByMail(@RequestParam("mail") String mail);
}
