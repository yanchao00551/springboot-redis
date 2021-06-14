package com.power.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.power.common.utils.Constant;
import com.power.modules.sys.dao.SysMenuDao;
import com.power.modules.sys.entity.SysMenuEntity;
import com.power.modules.sys.service.SysMenuService;
import com.power.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity>implements SysMenuService {
    @Autowired
    SysUserService sysUserService;
    /**
     *  根据parentId查询菜单列表
     */
    public List<SysMenuEntity> queryListParentId(Long parentId,List<Long> menuIdList){
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }
        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for(SysMenuEntity menu:menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L,menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList,menuIdList);

        return menuList;
    }

    /**
     * 递归获取子菜单
     * @param
     * @return
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList ,List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<>();
        for(SysMenuEntity entity: menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(),menuIdList),menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }



    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);

        return getAllMenuList(menuIdList);
    }
}
