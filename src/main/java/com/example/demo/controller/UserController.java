package com.example.demo.controller;

import com.example.demo.config.MyWebConfiguration;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.Result;
import com.example.demo.exception.ResultCode;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Result hello(){
        return new Result(ResultCode.SUCCESS,"hello world");
    }

    //登陆，登陆成功返回用户信息
    @GetMapping("/login")
    public Result login(@RequestParam(value = "account") String account,
                        @RequestParam(value = "password") String password,
                        HttpSession session) {
        UserEntity user = userService.checkLogin(account, password);
        session.setAttribute(MyWebConfiguration.SESSION_KEY,account);
        return new Result(ResultCode.SUCCESS, user);
    }

    //返回用户信息
    @GetMapping("/getuser")
    public Result getUser(@RequestParam(value = "account") String account) {
        UserEntity user = userService.getUser(account);
        return new Result(ResultCode.SUCCESS, user);
    }


    //修改用户密码 没仔细考虑异常的处理
    @GetMapping("/altpsd")
    public Result alertPassword(@RequestParam(value = "account") String account,
                                @RequestParam(value = "password") String password) {
        if (userService.alertPassword(account, password) == 1) {
            return new Result(ResultCode.SUCCESS, "修改密码成功");
        } else {
            return new Result(ResultCode.FAILURE, "修改密码失败");
        }
    }


    //注册用户
    @GetMapping("/register")
    public Result register(@RequestParam(value = "account") String account,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "name") String name) {
        if (userService.register(account, password, name) == 1) {
            return new Result(ResultCode.SUCCESS, "注册账户成功");
        } else {
            return new Result(ResultCode.FAILURE, "注册账户失败");
        }
    }
}
