package com.power.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.power.modules.sys.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 查询用户菜单列表
     * @param userId
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long userId);

    /**
     * 根据父id查询菜单
     * @param parentId
     * @return
     */
    public List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 根据父id查询菜单
     * @param parentId
     * @param menuIdList
     * @return
     */
    public List<SysMenuEntity> queryListParentId(Long parentId,List<Long> menuIdList);



}
