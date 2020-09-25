package com.wzp.module.core.dto;

import cn.hutool.core.util.StrUtil;
import com.wzp.module.core.pojo.Role;
import lombok.Data;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */
@Data
public class UserDto {

    private String loginId;
    private Long phone;
    private String mail;
    private String password;
    private String address;
    private Role role;

    public boolean isNull() {
        return !StrUtil.isNotBlank(loginId);
    }

}
