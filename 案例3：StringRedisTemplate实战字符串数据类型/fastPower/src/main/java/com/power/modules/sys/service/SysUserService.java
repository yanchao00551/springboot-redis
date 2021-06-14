package com.power.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.power.common.utils.PageUtils;
import com.power.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 分页
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String,Object> params);

    SysUserEntity queryByUserName(String username);

    /**
     *  修改密码
     * @param userId
     * @param password 原始密码
     * @param newPassword 新密码
     * @return
     */
    boolean updatePassword(Long userId,String password,String newPassword);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
