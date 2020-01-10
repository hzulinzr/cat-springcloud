//package com.lin.config;
//
//import com.alibaba.nacos.common.util.HttpMethod;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
///**
// * @author lzr
// * @date 2020-01-09 11:06:46
// */
//@Configuration
//@EnableResourceServer
//public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {
//    private static final String RESOURCE_IDS = "*";
//
//    private AuthenticationManager manager;
//
//    private Oauth2AccessDecisionManager accessDecisionManager;
//
//    private Oauth2FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    public AuthResourceServerConfig(AuthenticationManager manager, Oauth2AccessDecisionManager accessDecisionManager, Oauth2FilterInvocationSecurityMetadataSource securityMetadataSource) {
//        this.manager = manager;
//        this.accessDecisionManager = accessDecisionManager;
//        this.securityMetadataSource = securityMetadataSource;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_IDS).stateless(true);
//    }
//    /**
//     * API权限控制
//     * 过滤器优先度在 FilterSecurityInterceptor 之后
//     * @return
//     */
//    private Oauth2FilterSecurityInterceptor createApiAuthenticationFilter() {
//        Oauth2FilterSecurityInterceptor interceptor = new Oauth2FilterSecurityInterceptor();
//        interceptor.setAuthenticationManager(manager);
//        interceptor.setAccessDecisionManager(accessDecisionManager);
//        interceptor.setSecurityMetadataSource(securityMetadataSource);
//        return interceptor;
//    }
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterAfter(createApiAuthenticationFilter(), FilterSecurityInterceptor.class);
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and().requestMatchers().anyRequest()
//                .and().anonymous()
//                .and().authorizeRequests()
////                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
//                //配置访问权限控制，必须认证过后才可以访问
//                .antMatchers("/**").authenticated();
//        //请求权限配置
//        httpSecurity.authorizeRequests()
//                //下边的路径放行,不需要经过认证
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/user/register").permitAll()
//                //OPTIONS请求不需要鉴权
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .antMatchers("/configuration/ui").permitAll()
//                .antMatchers("/configuration/security").permitAll();
////                //用户的增删改接口只允许管理员访问
////                .antMatchers(HttpMethod.POST, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
////                .antMatchers(HttpMethod.PUT, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
////                .antMatchers(HttpMethod.DELETE, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
////                //获取角色 权限列表接口只允许系统管理员及高级用户访问
////                .antMatchers(HttpMethod.GET, "/auth/role").hasAnyAuthority(ROLE_ADMIN)
////                //其余接口没有角色限制，但需要经过认证，只要携带token就可以放行
////                .anyRequest()
////                .authenticated();
//
//    }
//}
