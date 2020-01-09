package com.lin.config.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-09 11:10:16
 */
@Data
public class AuthDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;

    public AuthDetails(){

    }

    public AuthDetails(AuthDetails user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.username;
    }

    @Override
    public String getUsername() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
