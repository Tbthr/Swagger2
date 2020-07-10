package com.lyq.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体")
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ApiModelProperty("用户名")
    public String username;

    @ApiModelProperty("密码")
    public String password;
}