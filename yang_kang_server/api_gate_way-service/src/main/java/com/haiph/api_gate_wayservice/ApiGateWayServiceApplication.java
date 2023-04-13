package com.haiph.api_gate_wayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServiceApplication.class, args);
    }

}
