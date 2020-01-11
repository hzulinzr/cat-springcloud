//package com.lin.config.resource.filter;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
///**
//* 资源源数据定义，即定义某一资源可以被哪些角色访问
//* @Author: wrc
//* @Date: 2020/1/1
//*/
//@Slf4j
//@Component
//public class Oauth2FilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        //TODO 测试环境不做权限验证
////        if ("/user/profile".equals(((FilterInvocation) object).getRequestUrl())) {
////            // [/user/profile] 不需要鉴权
////            return null;
////        }
//        //根据url查询所需要的权限
//        if (object instanceof FilterInvocation) {
//            FilterInvocation fi = (FilterInvocation) object;
//            String requestUrl = fi.getRequestUrl();
//            List roleList = new ArrayList<>();
//            roleList.add(requestUrl);
//            //查询url
//            String[] roleArray = new String[roleList.size()];
//            roleArray = (String[]) roleList.toArray(roleArray);
//            return SecurityConfig.createList(roleArray);
//        }
//        return Collections.EMPTY_LIST;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//}
