package com.jerry.springboot_transaction_test.web;

import com.jerry.springboot_transaction_test.dao.UserDao;
import com.jerry.springboot_transaction_test.pojo.User;
import com.jerry.springboot_transaction_test.service.UserService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @PostMapping("/test1")
    public boolean test1(@RequestBody User user)
    {
        try {
            userService.test1(user);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("最后查询数据"+userDao.findById(user.getId()));
        return true;
    }
    @PostMapping("/test2")
    public boolean test2(@RequestBody User user)
    {
      userService.test2(user);
        System.out.println("最后查询数据"+userDao.findById(user.getId()));
        return true;
    }
    @PostMapping("/test3")
    public boolean test3(@RequestBody User user)
    {
        userService.test3(user);
        System.out.println("最后查询数据"+userDao.findById(user.getId()));
        return true;
    }
    @PostMapping("/test4")
    public boolean test4(@RequestBody User user)
    {
        userService.test4(user);
        System.out.println("最后查询数据"+userDao.findById(user.getId()));
        return true;
    }
}
