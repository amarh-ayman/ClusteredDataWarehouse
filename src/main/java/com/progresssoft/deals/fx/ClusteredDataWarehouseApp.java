package com.progresssoft.deals.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ClusteredDataWarehouseApp {
    public static void main(String[] args) {
        SpringApplication.run(ClusteredDataWarehouseApp.class, args);
    }
}