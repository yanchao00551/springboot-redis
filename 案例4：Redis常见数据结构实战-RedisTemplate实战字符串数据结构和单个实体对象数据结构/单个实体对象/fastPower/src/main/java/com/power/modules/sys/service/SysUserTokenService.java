package com.power.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.power.common.utils.R;
import com.power.modules.sys.entity.SysUserTokenEntity;

public interface SysUserTokenService  extends IService<SysUserTokenEntity> {
    /**
     * 生成token
     */
    R createToken(long userId);

    /**
     * 退出修改token值
     */
    void logout(long userId);
}
