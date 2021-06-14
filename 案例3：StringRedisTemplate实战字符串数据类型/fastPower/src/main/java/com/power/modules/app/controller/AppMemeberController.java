package com.power.modules.app.controller;

import com.power.common.utils.R;
import com.power.modules.app.annotation.Login;
import com.power.modules.app.annotation.LoginUser;
import com.power.modules.app.entity.UserEntity;
import com.power.modules.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Api(tags = "用户中心管理")
public class AppMemeberController {
    @Autowired
    UserService userService;

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userinfo(@LoginUser UserEntity user){
        return R.ok().put("user",user);
    }



}
