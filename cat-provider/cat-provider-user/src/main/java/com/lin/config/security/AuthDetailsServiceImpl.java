package com.lin.config.security;

import com.lin.dao.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lzr
 * @date 2020-01-09 11:13:05
 */
@Service
public class AuthDetailsServiceImpl implements UserDetailsService {

   private UserMapper userMapper;

    public AuthDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDetails user = userMapper.getUser(username);
        if(null == user){
            throw new UsernameNotFoundException("Invalid username");
        }
        return new AuthDetails(user);
    }
}
