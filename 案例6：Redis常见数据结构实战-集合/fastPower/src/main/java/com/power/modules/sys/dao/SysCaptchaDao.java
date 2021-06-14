package com.power.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.power.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {
}
