package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lzr
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class GatewayRoutes {
    public static void main(String[] args) {
        SpringApplication.run(GatewayRoutes.class, args);
    }
}
