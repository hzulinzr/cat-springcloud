//package com.lin.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class AuthFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
//        //返回401状态码和提示信息
//        if(StringUtils.isBlank(token)){
//            ServerHttpResponse response = exchange.getResponse();
//            JSONObject message = new JSONObject();
//            message.put("status", -1);
//            message.put("data", "鉴权失败");
//            byte[] bytes = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bytes);
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//
//        }
//
//
////        //重定向(redirect)到登录页面
////        if (StringUtils.isBlank(token)) {
////            String url = "localhost:5001/cat/user";
////            ServerHttpResponse response = exchange.getResponse();
////            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
////            response.setStatusCode(HttpStatus.SEE_OTHER);
////            response.getHeaders().set(HttpHeaders.LOCATION, url);
////            return response.setComplete();
////        }
//        return chain.filter(exchange);
//
//    }
//
//    @Override
//    public int getOrder() {
//        return -100;
//    }
//}
