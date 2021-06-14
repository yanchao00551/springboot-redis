package com.power.modules.sys.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.power.common.utils.Constant;
import com.power.common.utils.PageUtils;
import com.power.common.utils.R;
import com.power.modules.sys.entity.SysCaptchaEntity;
import com.power.modules.sys.entity.SysUserEntity;
import com.power.modules.sys.form.PasswordForm;
import com.power.modules.sys.form.SysLoginForm;
import com.power.modules.sys.service.SysCaptchaService;
import com.power.modules.sys.service.SysUserService;
import com.power.modules.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
@Api(tags = "后台用户Shiro登录")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    SysUserTokenService sysUserTokenService;
    @Autowired
    SysCaptchaService sysCaptchaService;

    /**
     * 获取验证码
     * uuid 是使用vue.js 生成的
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,String uuid) throws ServletException,IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    @ApiOperation("登录")
    public Map<String,Object> login(@RequestBody SysLoginForm form) throws IOException{
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
        //账号不存在、或者密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(),user.getSalt()).toHex())){
            return R.error("账号或密码不正确");
        }
        //账号锁定
        if(user.getStatus() == 0){
            return R.error("账号已被锁定，请联系管理员");
        }

        //验证码验证
        Wrapper<SysCaptchaEntity> wrapper = new EntityWrapper<SysCaptchaEntity>().eq("uuid",form.getUuid());
        SysCaptchaEntity sysCaptchaEntity = sysCaptchaService.selectOne(wrapper);
        String dbCaptcha = sysCaptchaEntity.getCode();
        if(dbCaptcha == null || !dbCaptcha.equals(form.getCaptcha())){
            sysCaptchaService.delete(wrapper);
            return R.error("验证码输入错误，请重新输入");
        }else{
            sysCaptchaService.delete(wrapper);
        }


        //生成token,并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }

    @PostMapping("/sys/password")
    public R password(@RequestBody PasswordForm from){
        //sha256加密
        //from.getPassword(): admin
        //getUser().getSalt(): YzcmCZNvbXocrsz9dm8e
        // password 值：9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d
        String password = new Sha256Hash(from.getPassword(),getUser().getSalt()).toHex();
        //sha256加密
        //from.getNewPassword()  :  123456
        //getUser().getSalt: YzcmCZNvbXocrsz9dm8e
        //password: ?  新的 长字符串
        String newPassword = new Sha256Hash(from.getNewPassword(),getUser().getSalt()).toHex();
        boolean flag = sysUserService.updatePassword(getUserId().longValue(),password,newPassword);
        if(!flag){
            return R.error("源密码不正确");
        }
        return R.ok();
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    @ApiOperation("退出")
    public R logout(){
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }

    @GetMapping("/list")
    @ApiOperation("获取系统用户列表")
    @RequiresPermissions(value = "asdfasfd")
    public R list(@RequestParam Map<String,Object> params){
        //只要超级管理员，才能查看所有管理员列表
        if(getUserId() != Constant.SUPER_ADMIN){
            params.put("createUserId",getUserId());
        }
        //分页
        PageUtils page= sysUserService.queryPage(params);
        return R.ok().put("page",page);
    }
}
