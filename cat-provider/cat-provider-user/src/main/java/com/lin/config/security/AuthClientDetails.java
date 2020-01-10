package com.lin.config.security;

import com.lin.model.AuthClient;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
* 客户端详情实体
* @Author: wrc
* @Date: 2020/1/2
*/
@Data
public class AuthClientDetails implements ClientDetails {

    private static final long serialVersionUID = 7992659824052153354L;

    private String clientId;

    private String secret;

    private Set<String> authorizedGrantTypes;

    private Set<String> scope;

    private  Collection<GrantedAuthority> authorities;

    public AuthClientDetails(AuthClient authClient){
        Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add("password");
        authorizedGrantTypes.add("client_credentials");
        authorizedGrantTypes.add("refresh_token");
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.scope = authClient.getScope();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (null != authClient.getAuthorities()){
            authClient.getAuthorities().forEach(authoritie -> authorities.add(new SimpleGrantedAuthority(authoritie)));
        }
        this.authorities = authorities;
        this.clientId = authClient.getClientId();
        this.secret = authClient.getSecret();
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return this.secret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
