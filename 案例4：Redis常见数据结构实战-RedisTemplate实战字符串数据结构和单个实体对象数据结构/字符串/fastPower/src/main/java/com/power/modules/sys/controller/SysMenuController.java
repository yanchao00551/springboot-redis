package com.power.modules.sys.controller;

import com.power.common.utils.R;
import com.power.modules.sys.entity.SysMenuEntity;
import com.power.modules.sys.service.ShiroService;
import com.power.modules.sys.service.SysMenuService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{
    @Autowired
    ShiroService shiroService;
    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/nav")
    public R nav(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return R.ok().put("menuList",menuList).put("permissions",permissions);
    }


}
