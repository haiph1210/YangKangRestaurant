//package com.haiph.salesservice.configuration;
//
//import org.apache.hc.client5.http.classic.HttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class ConfigRes {
////    @Bean
////    public void providePool() {
////        HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
////        ((PoolingHttpClientConnectionManager) connectionManager).setMaxTotal(20);
////        ((PoolingHttpClientConnectionManager) connectionManager).setDefaultMaxPerRoute(10);
////        HttpClient httpClient = HttpClients.custom()
////                .setConnectionManager((org.apache.hc.client5.http.io.HttpClientConnectionManager) connectionManager)
////                .build();
////        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
////        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
////    }
//
//    @Bean
//    public void providePool2() {
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(20);
//        connectionManager.setDefaultMaxPerRoute(10);
//        HttpClient httpClient = HttpClients.custom()
//                .setConnectionManager((org.apache.hc.client5.http.io.HttpClientConnectionManager) connectionManager)
//                .build();
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
//    }
//
//}
