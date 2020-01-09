package com.lin.config;

import com.lin.config.security.AuthDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author lzr
 * @date 2020-01-09 11:07:35
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;

    private final RedisConnectionFactory redisConnectionFactory;

    private final AuthDetailsServiceImpl authDetailsService;

    public AuthAuthorizationServerConfig(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory, AuthDetailsServiceImpl authDetailsService) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.authDetailsService = authDetailsService;
    }


    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1")
                .authorizedGrantTypes("password")
                .scopes("select")
                .secret(new BCryptPasswordEncoder().encode("123456"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints.tokenStore(tokenStore())
                //.accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager).reuseRefreshTokens(true)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(authDetailsService);

    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter () {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
