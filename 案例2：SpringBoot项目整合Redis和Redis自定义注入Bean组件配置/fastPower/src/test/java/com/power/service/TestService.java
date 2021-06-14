package com.power.service;

import com.power.modules.app.entity.UserEntity;
import com.power.modules.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testInsert(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("多数据源读写分离测试a");
        userEntity.setMobile("13248363333");
        userEntity.setUserStatus(1);
        userEntity.setUserHeadimg("/img/111.jpg");
        userEntity.setIsSystem(1);
        userEntity.setIsMember(1);
        userEntity.setUserTel("13248361234");
        userEntity.setUserTelBind(1);
        userEntity.setQqOpenid("wxoaowoosaosdhfOI");
        userEntity.setQqInfo("qq222222");
        userEntity.setUserEmail("stackoverflow0412@gmail.com");
        userEntity.setUserEmailBind(1);
        userEntity.setWxIsSub(1);
        userEntity.setWxUnionid("woshywn");
        userEntity.setQrcodeTemplateId(1);
        Integer rstId = userService.registerUser(userEntity);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(rstId.toString());
    }

    @Test
    public void testSelect(){
        UserEntity rst = null;
        rst = userService.queryByMobile("13248363333");
        System.out.println(rst.toString());
    }

}