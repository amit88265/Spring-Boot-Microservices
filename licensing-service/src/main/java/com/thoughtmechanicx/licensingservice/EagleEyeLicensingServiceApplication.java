package com.thoughtmechanicx.licensingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableResourceServer
public class EagleEyeLicensingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleEyeLicensingServiceApplication.class, args);
    }

}
