package com.lin.config.security;

import com.lin.dao.AuthUserMapper;
import com.lin.dao.ResourceMapper;
import com.lin.model.AuthClient;
import com.lin.model.AuthUser;
import com.lin.model.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzr
 * @date 2020-01-09 15:55:39
 * 验证客户端
 */
@Slf4j
@Service
public class AuthClientDetailsService implements ClientDetailsService {

    private AuthUserMapper authUserMapper;
    private ResourceMapper resourceMapper;

    public AuthClientDetailsService(AuthUserMapper authUserMapper, ResourceMapper resourceMapper) {
        this.authUserMapper = authUserMapper;
        this.resourceMapper = resourceMapper;
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        AuthClient authClient = new AuthClient();
        //查用户信息
        AuthUser authUser = authUserMapper.getUser(clientId);
        authClient.setClientId(authUser.getClientId());
        authClient.setSecret(authUser.getSecret());
        //查询数据库用户的权限
        List<Resource> resourceByRelevanceId = resourceMapper.getResource(clientId);
        List<String> authorities =new ArrayList();
        Set<String> scope=new HashSet<>();
        resourceByRelevanceId.forEach((resource)->{
            authorities.add(resource.getUrl());
            scope.add(resource.getScope());
        });
        authClient.setScope(scope);
        authClient.setAuthorities(authorities);
        return new AuthClientDetails(authClient);
    }
}
