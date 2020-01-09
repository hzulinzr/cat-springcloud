package com.lin.filter;

import com.lin.jwt.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author lzr
 * @date 2019/11/11 4:36 下午
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
//    private static final String AUTHORIZE_TOKEN = "token";
//    private static final String AUTHORIZE_UID = "uid";

    private final StringRedisTemplate stringRedisTemplate;

    public AuthorizeFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        String loginUrl = "/cat/user/login";
        if (loginUrl.equals(requestPath)) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(null == token){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();

        }

        //请求中的token是否在redis中存在
        boolean verifyResult = JwtUtil.verify(token);
        if(!verifyResult){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(AUTHORIZE_TOKEN);
//        String uid = headers.getFirst(AUTHORIZE_UID);
//        if (token == null) {
//            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
//        }
//        if (uid == null) {
//            uid = request.getQueryParams().getFirst(AUTHORIZE_UID);
//        }
//
//        ServerHttpResponse response = exchange.getResponse();
//        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return fallBack("/cat/user/login", exchange);
//            //return response.setComplete();
//        }
//        //判断通过uid获取的token是否和给定的token一致
//        String authToken = stringRedisTemplate.opsForValue().get(uid);
//        if (authToken == null || !authToken.equals(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return fallBack("/cat/user/login", exchange);
//            //return response.setComplete();
//        }

        return chain.filter(exchange);
    }

    private Mono<Void> fallBack(String url, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", url);
        return exchange.getResponse().setComplete();
    }


    @Override
    public int getOrder() {
        return -100;
    }
}
