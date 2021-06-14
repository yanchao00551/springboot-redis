package com.power.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.power.common.utils.PageUtils;
import com.power.common.utils.Query;
import com.power.modules.sys.dao.SysUserDao;
import com.power.modules.sys.entity.SysUserEntity;
import com.power.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Override
    public SysUserEntity queryByUserName(String username) {

        return baseMapper.queryByUserName(username);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");
        Long createUserId = (Long)params.get("createUserId");

        Page<SysUserEntity> page = this.selectPage(
                new Query<SysUserEntity>(params).getPage(),
                new EntityWrapper<SysUserEntity>()
                .like(StringUtils.isNotBlank(username),"username",username)
                .eq(createUserId !=null,"create_user_id",createUserId)
        );
        return new PageUtils(page);
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,new EntityWrapper<SysUserEntity>().eq("user_id",userId).eq("password",password));
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }
}
