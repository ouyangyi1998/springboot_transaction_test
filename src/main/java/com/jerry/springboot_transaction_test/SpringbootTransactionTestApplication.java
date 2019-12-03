package com.jerry.springboot_transaction_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootTransactionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTransactionTestApplication.class, args);
    }

}
