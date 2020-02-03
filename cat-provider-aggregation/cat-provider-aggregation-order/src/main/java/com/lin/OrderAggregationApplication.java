package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lzr
 * @date 2020-02-03 14:27:54
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class OrderAggregationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderAggregationApplication.class, args);
    }
}
