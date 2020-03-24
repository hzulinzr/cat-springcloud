package com.lin.filter;

import com.lin.model.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-09 18:00:27
 */
@Slf4j
@Configuration
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZATION="Authorization";



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return Mono.defer(() -> {
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, OPTIONS, DELETE, PATCH");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            authToken(exchange);
            return chain.filter(exchange);
        });
    }
    /**
     * 鉴权
     */
    private void authToken(ServerWebExchange exchange) {
        //不验证的地址
        List<String> urls = new ArrayList<>();
        urls.add("/cat/user/login");
        urls.add("/cat/user/logout");
        urls.add("/cat/user/register");
        urls.add("/cat/book/list");
        urls.add("/cat/book/info");
        urls.add("/cat/book/info/list");
        urls.add("/cat/order/insert");
        urls.add("/cat/order/finish");
        urls.add("/aliPay");
        urls.add("/cat/user/list");
        urls.add("/cat/order/list");
//        urls.add("/cat/cart/add");
//        urls.add("/cat/cart/adjust");
//        urls.add("/cat/cart/list");
//        urls.add("/cat/cart/delete");
        String requestPath = exchange.getRequest().getPath().value();
        //特殊url不验证
        if (urls.contains(requestPath)) {
            return;
        }
        // header填充
        List<String> authorization = exchange.getRequest().getHeaders().get(AUTHORIZATION);
        //验证assess_token是否有效
        if (null == authorization || authorization.size() == 0) {
            log.error("用户token不存在");
            throw new RuntimeException("token不存在");
        }

        //获取用户信息
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, "Bearer" + authorization.get(0));
        HttpEntity<MultiValueMap<String, Object>> requests = new HttpEntity(null, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthClient> exchange1 = restTemplate.exchange("http://localhost:8071/user/info", HttpMethod.GET, requests, AuthClient.class);
        AuthClient body = exchange1.getBody();
        if (null == body || null == body.getAuthorities() || 0 == body.getAuthorities().size()) {
            log.error("获取用户信息异常");
            throw new RuntimeException("获取用户信息异常");
        }
//        log.info("权限： {}",body.getAuthorities());
//        if (!body.getAuthorities().contains(requestPath)) {
//            log.error("用户权限不足");
//            throw new RuntimeException("用户权限不足");
//        }
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
