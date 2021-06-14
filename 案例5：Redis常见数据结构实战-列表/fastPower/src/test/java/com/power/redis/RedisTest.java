package com.power.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    //由于之前已经自定义注入RedisTemplate组件，因而在此可以直接自动装配
    @Autowired
    private RedisTemplate redisTemplate;

    //定义StringRedisTemplate操作组件
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //定义Json序列化和反序列化框架类
    @Autowired
    private ObjectMapper objectMapper;

    //采用RedisTemplate将字符串信息写入缓存中读取出来
    @Test
    public void one(){
        log.info("--------开始RedisTemplate实战字符串信息--------");
        //定义字符串内容及存入缓存的key
        final String content = "RedisTemplate实战字符串信息";
        final String key = "redis:template:one:string";
        //Redis通用操作组件
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{}",content);
        valueOperations.set(key,content);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        log.info("读取出来的内容：{}",result);
    }

    //采用RedisTemplate将对象信息序列化为JSON格式字符串后写入缓存中
    //然后将其读取出来，最后反序列化解析其中的内容并展示在控制台上
    @Test
    public void two() throws Exception{
        log.info("-----开始RedisTemplate操作组件实例------");
        //构造对象信息
        User user = new User(1,"stackoverflow","颜超");
        //Redis通用的操作组件
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //将序列化后的信息写入缓存中
        final String key = "redis.template:two:object";
        final String context = objectMapper.writeValueAsString(user);
        valueOperations.set(key,context);
        log.info("写入缓存对象的信息：{}",user);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        if(result != null){
            User resultUser = objectMapper.readValue(result.toString(),User.class);
            log.info("读取缓存内容并反序列化后的结果：{}",resultUser);
        }
    }

    //采用StringRedisTemplate将字符串信息写入缓存中并读取出来
    @Test
    public void three(){
        log.info("------开始StringRedisTemplate操作组件实战------");
        //定义字符串内容及存入缓存的key
        final String content = "StringRedisTemplate实战字符串信息";
        final String key = "redis:three";
        //Redis通用的操作组件
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{}",content);
        valueOperations.set(key,content);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        log.info("读取出来的内容：{}",result);
    }

    //列表类型
    @Test
    public void list() throws Exception{
        //构造已经排好序的用户对象列表
        List<String> list = new ArrayList<>();
        list.add(objectMapper.writeValueAsString(new User(1,"stackoverflow","yanchao")));
        list.add(objectMapper.writeValueAsString(new User(2,"大圣", "jack")));
        list.add(objectMapper.writeValueAsString(new User(3,"盘古","Lee")));
        log.info("构造已经排好序的用户对象列表：{}",list);
        //将列表数据存储至Redis的List中
        final String key = "redis:test:2";
        ListOperations listOperations = redisTemplate.opsForList();
        for(String u:list){
            //往列表中添加数据-从队尾中添加  先进先出
            listOperations.leftPush(key,u);
        }
        //获取Redis中List的数据——从队头中遍历获取，直到没有元素为止 读取一个元素则删掉这个元素
        log.info("---获取Redis中List的数据-从队头中获取--");
        Object res = null;
        res = listOperations.rightPop(key);
        while (res != null){
            User user = objectMapper.readValue(res.toString(),User.class);
            log.info("当前数据：{}",user);
            res =listOperations.rightPop(key); //读取一个元素则删掉这个元素
        }
    }


}
