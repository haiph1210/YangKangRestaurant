package com.haiph.restaurant_infoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestaurantInfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantInfoServiceApplication.class, args);
    }
    @Bean
    public RestTemplate provideRes() {
        return  new RestTemplate();
    }
}
