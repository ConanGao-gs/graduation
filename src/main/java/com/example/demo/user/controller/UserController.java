package com.example.demo.user.controller;


import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 高爽
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userService.list();
    }

    @PostMapping("/addUser")
    public String addUser() {
        return "添加用户";
    }
}
