package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //登陆检查，账号密码正确时
    public UserEntity checkLogin(String account, String password) {
        UserEntity user = userDao.getUser(account);
        if (user.getPassword().trim().equals(password)) {
            return user;
        } else {
            throw new DescribeException(ResultCode.LOGIN_ERROR);
        }
    }

    //根据用户名获取用户的信息
    public UserEntity getUser(String account){
        return userDao.getUser(account);
    }

    //修改用户密码 默认账户名是正确的
    public int alertPassword(String account,String password){
        return userDao.alertPassword(account,password);
    }

    //添加用户
    public int register(String account,String password,String name){
        return userDao.insertUser(account,password,name);
    }
}
