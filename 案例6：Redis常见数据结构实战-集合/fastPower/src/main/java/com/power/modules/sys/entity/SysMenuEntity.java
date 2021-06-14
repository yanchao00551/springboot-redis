package com.power.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 */
@TableName("sys_menu")
@Data
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;

    /**
     * 父菜单ID， 一级菜单ID 0
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权码（多个用逗号分隔，如：user:list,user:create
     */
    private String perms;

    /**
     * 类型 0：目录  1：菜单   2：按钮
     */
    private  Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    @TableField(exist = false)
    private List<?> list;

}
