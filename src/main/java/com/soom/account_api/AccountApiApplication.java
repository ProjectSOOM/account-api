package com.soom.account_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class AccountApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

}
