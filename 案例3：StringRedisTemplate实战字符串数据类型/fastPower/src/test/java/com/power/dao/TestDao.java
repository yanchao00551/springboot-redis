package com.power.dao;

import com.power.modules.app.dao.UserUnitDao;
import com.power.modules.app.entity.UserUnitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserUnitDao userUnitDao;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testInsert(){
        UserUnitEntity userUnitEntity = new UserUnitEntity();
        userUnitEntity.setUserName("测试");
        userUnitEntity.setRealName("昵称");
        userUnitEntity.setPassWord("密码");
        userUnitDao.insert(userUnitEntity);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(userUnitEntity.getId());
    }

}
