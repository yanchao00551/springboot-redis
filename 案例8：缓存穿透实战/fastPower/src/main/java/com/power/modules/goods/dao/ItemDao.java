package com.power.modules.goods.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.power.modules.goods.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemDao extends BaseMapper<ItemEntity> {
    //根据商品编码，查询商品详情
    ItemEntity selectByCode(@Param("code") String code);
}
