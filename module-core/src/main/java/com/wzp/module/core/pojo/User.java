package com.wzp.module.core.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends AbstractPojo {

    private String loginId;
    private Long phone;
    private String mail;
    private String password;
    private String address;
    @TableField(exist = false)
    private Role role;

}
