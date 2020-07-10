package com.lyq.swagger.controller;

import com.lyq.swagger.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @ApiOperation("接口名称")
    @PostMapping("/getUser")
    public User getUser(@ApiParam("用户名") String username, @ApiParam("密码") String password) {
        return new User(username, password);
    }
}
