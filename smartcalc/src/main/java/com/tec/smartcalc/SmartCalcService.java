package com.tec.smartcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients(basePackages = "com.tec")
@SpringBootApplication
@EnableAsync
public class SmartCalcService {
    public static void main(String[] args) {
        SpringApplication.run(SmartCalcService.class, args);
    }
}