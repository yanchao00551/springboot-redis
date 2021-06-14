package com.power.modules.app.controller;

import com.power.modules.app.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理相关接口")
public class HelloController {


    @ResponseBody
    @PostMapping("/index")
    @ApiOperation("添加一个用户")
    public Map<String,Object> Index(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        return map;
    }
}
