package com.wzp.module.core.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("role")
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractPojo implements Serializable {

    private String name;

    private Integer code;

    private boolean superAdmin;

    @TableField(exist = false)
    private List<Authority> authorityList;


}
