package com.lin.service.impl;

import com.lin.model.AuthClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
* 查询用户信息和权限
 * @author lzr
 */
@Service
public class AuthClientServiceImpl{


    /**
    * 获得用户验证数据、权限等等
    */
    public AuthClient getAuthClient(Authentication authentication) {
        AuthClient authClient=new AuthClient();
        List<String> authorities=new ArrayList<>();
        Collection<? extends GrantedAuthority> authoritie = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authoritie.iterator();
        while (iterator.hasNext()){
            String authority = iterator.next().getAuthority();
            authorities.add(authority);
        }
        authClient.setAuthorities(authorities);
        authClient.setClientId(authentication.getName());
        return authClient;
    }
}
