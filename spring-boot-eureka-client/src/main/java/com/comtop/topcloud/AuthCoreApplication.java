package com.comtop.topcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.comtop.topcloud.mapper")
public class AuthCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCoreApplication.class, args);
    }

}