package com.jerry.springboot_transaction_test.dao;

import com.jerry.springboot_transaction_test.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Insert("insert into user(id,name,age) values(#{id},#{name},#{age})")
    void insert(User user);
    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    void update(User user);
    @Select("select * from user where id=#{id}")
    User findById(long id);
}
