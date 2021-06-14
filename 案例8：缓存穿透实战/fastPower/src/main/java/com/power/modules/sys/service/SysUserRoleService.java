package com.power.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.power.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRoleEntity> {
    void saveOrUpdate(Long userId, List<Long>roleIdList);

    /**
     * 根据用户ID获取角色ID列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
