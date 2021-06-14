package com.power.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 用户与角色对应关系
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}
