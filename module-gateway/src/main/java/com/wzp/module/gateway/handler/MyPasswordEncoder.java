package com.wzp.module.gateway.handler;

import cn.hutool.crypto.SecureUtil;
import com.wzp.module.core.constant.UserConstant;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/21
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword + UserConstant.PASSWORD_MD5_SALT);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawEncode = SecureUtil.md5(rawPassword + UserConstant.PASSWORD_MD5_SALT);
        return rawEncode.equals(encodedPassword);
    }
}
