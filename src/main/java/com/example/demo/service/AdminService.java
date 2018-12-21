package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.AdminEntity;
import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public int getUser(String account, String password) {
        AdminEntity admin = adminDao.getUser(account);
        if (admin.getPassword().trim().equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }

    public void addBook(String ISBN, String classify, String title, String author, String press, String preDate, int duplicate) {
        int result = adminDao.addBook(ISBN, classify, title, author, press, preDate, duplicate);
        if (result!=1){
            throw new DescribeException(ResultCode.FAILURE);
        }
    }

    public void removeBook(String ISBN){
        int result = adminDao.removeBook(ISBN);
        if (result!=1){
            throw new DescribeException(ResultCode.BOOK_NOT_FOUNT);
        }
    }
}
