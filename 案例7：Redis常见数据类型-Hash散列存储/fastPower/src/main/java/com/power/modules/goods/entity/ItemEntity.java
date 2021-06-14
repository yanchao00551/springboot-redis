package com.power.modules.goods.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息表
 */
@Data
@TableName("tb_item")
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @TableId
    private Integer id;

    /*
    商品编号
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 创建时间
     */

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


}
