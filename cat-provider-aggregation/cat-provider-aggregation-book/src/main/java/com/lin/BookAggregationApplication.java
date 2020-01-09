package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lzr
 * @date 2020-01-07 10:25:29
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class BookAggregationApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookAggregationApplication.class, args);
    }
}
