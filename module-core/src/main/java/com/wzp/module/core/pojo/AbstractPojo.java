package com.wzp.module.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */

public abstract class AbstractPojo implements Serializable {

    @TableId
    private Integer id;

    @TableField(fill = FieldFill.INSERT,value = "create_date",update = "now()")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()",value = "update_date")
    private Date updateDate;
}
