package com.power.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.power.modules.sys.entity.SysCaptchaEntity;

import java.awt.image.BufferedImage;

public interface SysCaptchaService extends IService<SysCaptchaEntity> {
    /*
    获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码校验
     */
    boolean validate(String uuid,String code);
}
