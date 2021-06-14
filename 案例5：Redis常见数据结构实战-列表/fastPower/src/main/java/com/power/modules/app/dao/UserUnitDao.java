package com.power.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.power.modules.app.entity.UserUnitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserUnitDao extends BaseMapper<UserUnitEntity> {

}
