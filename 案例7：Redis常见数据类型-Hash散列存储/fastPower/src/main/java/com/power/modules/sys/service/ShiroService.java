package com.power.modules.sys.service;

import com.power.modules.sys.entity.SysUserEntity;
import com.power.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * Shiro相关接口
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);


    /**
     * 通过token查询token信息
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     */
    SysUserEntity queryUser(Long userId);
}
