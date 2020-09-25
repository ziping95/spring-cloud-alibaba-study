package com.wzp.module.user.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzp.module.core.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/12
 */
public interface UserMapper extends BaseMapper<User> {

    User findUserByCondition(User user);

    List<User> findUserByIds(@Param("ids") List<Integer> ids);

    List<User> findUserByMap(@Param("mapIds") Map<Integer,Integer> ids);

    List<User> test(@Param("a") Boolean a,@Param("b") Boolean b,@Param("c") Boolean c);
}
