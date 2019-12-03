package com.jerry.springboot_transaction_test.service.impl;

import com.jerry.springboot_transaction_test.dao.UserDao;
import com.jerry.springboot_transaction_test.pojo.User;
import com.jerry.springboot_transaction_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    @Transactional
    public boolean test1(User user) throws Exception {
        long id=user.getId();
        System.out.println("查询的数据１"+dao.findById(id));
        dao.insert(user);
        System.out.println("查询的数据２"+dao.findById(id));
        dao.insert(user);
        return false;
    }

    @Override
    @Transactional
    public boolean test2(User user) {
        long id=user.getId();
        try{
            System.out.println("查询的数据１"+dao.findById(id));
            dao.insert(user);
            System.out.println("查询的数据２"+dao.findById(id));
            dao.insert(user);
        }catch (Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean test3(User user) {
        try{
            System.out.println("查询的数据１"+dao.findById(user.getId()));
            deal1(user);
            deal2(user);
            deal3(user);
        }catch (Exception e)
        {
            System.out.println("发生异常，手动回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }
    public void deal1(User user) throws Exception
    {
        dao.insert(user);
        System.out.println("查询的数据2"+dao.findById(user.getId()));
    }
    public void deal2(User user) throws Exception
    {
        if(user.getAge()<20)
        {
            dao.insert(user);
        }else
        {
            user.setAge(21);
            dao.update(user);
            System.out.println("查询的数据3"+dao.findById(user.getId()));
        }
    }
    @Transactional(rollbackFor = SQLException.class)
    public void deal3(User user)
    {
        if(user.getAge()>20)
        {
            dao.insert(user);
        }
    }

    @Override
    public boolean test4(User user) {
        TransactionStatus transactionStatus=null;
        boolean isCommit=false;
        try
        {
            transactionStatus=dataSourceTransactionManager.getTransaction(transactionDefinition);
            System.out.println("查询的数据１："+dao.findById(user.getId()));
            dao.insert(user);
            System.out.println("查询的数据2："+dao.findById(user.getId()));
            if(user.getAge()<20)
            {
                user.setAge(user.getAge()+2);
                dao.update(user);
                System.out.println("查询的数据3："+dao.findById(user.getId()));
            }else
            {
                throw new Exception("模拟异常");
            }
            dataSourceTransactionManager.commit(transactionStatus);
            isCommit=true;
            System.out.println("手动提交事物");
            throw new Exception("模拟异常");

        }catch (Exception e)
        {
            if(!isCommit)
            {
                System.out.println("发生异常，回滚");
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            e.printStackTrace();
        }
        return false;
    }
}
