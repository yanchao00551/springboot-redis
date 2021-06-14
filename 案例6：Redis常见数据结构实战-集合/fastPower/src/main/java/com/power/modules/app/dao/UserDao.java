package com.power.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.power.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 前台用户表
 * 
 * @author yanchao
 * @email 7631990@qq.com
 * @date 2018-06-26 09:40:09
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<UserEntity> {
	
}
