package com.jerry.springboot_transaction_test.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private Long id;
    private String name;
    private Integer age;
}
