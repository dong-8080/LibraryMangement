package com.example.demo.controller;

import com.example.demo.exception.Result;
import com.example.demo.exception.ResultCode;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/login")
    public Result login(@RequestParam(value = "account") String account,
                        @RequestParam(value = "password") String password) {
        int result = adminService.getUser(account, password);
        if (result == 1) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.LOGIN_ERROR);
        }
    }

    @GetMapping("/addbook")
    public Result addBook(@RequestParam(value = "isbn") String ISBN,
                          @RequestParam(value = "classify") String classify,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "author") String author,
                          @RequestParam(value = "press") String press,
                          @RequestParam(value = "preDate") String preDate,
                          @RequestParam(value = "duplicate") int duplicate) {
        adminService.addBook(ISBN, classify, title, author, press, preDate, duplicate);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/removebook")
    public Result removeBook(@RequestParam(value = "isbn") String ISBN) {
        adminService.removeBook(ISBN);
        return new Result(ResultCode.SUCCESS);
    }
}
