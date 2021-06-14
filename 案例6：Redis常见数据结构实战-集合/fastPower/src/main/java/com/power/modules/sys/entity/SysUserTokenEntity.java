package com.power.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@TableName("sys_user_token")
@Data
public class SysUserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户ID
    @TableId(type = IdType.INPUT)
    private Long userId;

    //token
    private String token;

    //过期时间
    private Date expireTime;

    //更新时间
    private Date updateTime;

}
