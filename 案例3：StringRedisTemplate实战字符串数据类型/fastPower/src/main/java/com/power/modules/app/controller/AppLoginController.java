package com.power.modules.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.power.common.utils.R;
import com.power.common.validator.ValidatorUtils;
import com.power.modules.app.entity.UserEntity;
import com.power.modules.app.form.LoginForm;
import com.power.modules.app.service.UserService;
import com.power.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * App登录授权控制器
 */
@RestController
@RequestMapping("/app")
@Api(tags = "前台用户JWT登录")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){

        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        long userId = userService.login(form);

        //生成token
        String token = jwtUtils.generateToken(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("expire",jwtUtils.getExpire());
        UserEntity entity = new UserEntity();
        entity.setLastLoginTime(new Date());
        userService.update(entity,new EntityWrapper<UserEntity>().eq("user_id",userId));
        return R.ok(map);

    }
}
