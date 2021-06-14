package com.power.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.power.modules.sys.entity.SysLogEntity;
import com.power.modules.sys.dao.SysLogDao;
import com.power.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysLogService")
public class SysLogServiceImpl  extends ServiceImpl<SysLogDao,SysLogEntity> implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public Integer insertSysLog(SysLogEntity sysLogEntity) {
        return sysLogDao.insert(sysLogEntity);
    }
}
