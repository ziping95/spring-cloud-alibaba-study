package com.wzp.module.core.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("authority")
public class Authority extends AbstractPojo {

    private String name;
    private String path;
}
