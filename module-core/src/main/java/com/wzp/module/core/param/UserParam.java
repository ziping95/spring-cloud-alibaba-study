package com.wzp.module.core.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */
@Data
public class UserParam {

    @NotNull
    private Long phone;
    @NotNull
    private String password;
}
