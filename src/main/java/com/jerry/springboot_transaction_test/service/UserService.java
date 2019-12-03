package com.jerry.springboot_transaction_test.service;

import com.jerry.springboot_transaction_test.pojo.User;

public interface UserService {
    boolean test1(User user)throws Exception;
    boolean test2(User user);
    boolean test3(User user);
    boolean test4(User user);
}
