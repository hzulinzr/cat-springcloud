package com.lin.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义密码验证
 * @author lzr
 */
@Slf4j
public class CustomPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword ) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            if(rawPassword.equals(encodedPassword)){
                return true;
            }
//            try {
//
//                if(MD5Util.validPassword(rawPassword.toString(),encodedPassword)) {
//                    return true;
//                }
//            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//                log.error("密码验证异常", e);
//            }
        }
        return false;
    }
}
