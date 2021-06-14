package com.power.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    //采用 RedisTemplate将字符串信息写入缓存中读取出来
    @Test
    public void one(){
        log.info("-------开始RedisTemplate字符串信息读取测试-------");
        final String key = "redis:template:one:string";
        final String content = "RedisTemplate字符串信息";
        //Redis String通用操作组件
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //将字符串信息写入缓存
        log.info("写入缓存中的内容：{}",content);
        valueOperations.set(key,content);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        log.info("读取出来的内容：{}",result);
    }

    @Test
    public void two() throws Exception{
        log.info("-------开始RedisTemplate Domain对象信息读取测试-------");
        //构造对象信息
        User user = new User(1,"stackoverflow","颜超");
        //Redis 通用操作组件
        ValueOperations valueOperations = redisTemplate.opsForValue();
        final String key = "redis:template:two:object";
        //user序列化为json字符串
        final String context = objectMapper.writeValueAsString(user);
        valueOperations.set(key,context);
        log.info("写入缓存对象的信息：{}",user);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        if(result != null){
            User resultUser = objectMapper.readValue(result.toString(),User.class);
            log.info("读取缓存内容并反序列化后的结果：{}",resultUser);
        }
      /*  valueOperations.set(key,user);
        log.info("写入缓存对象的信息：{}",user);
        //从缓存中读取内容
        User u = (User)valueOperations.get(key);
        log.info("读取缓存内容的结果：{}",u);*/

    }

    //采用StringRedisTemplate  将字符串信息写入缓存中并读取出来
    @Test
    public void three()throws Exception{
        log.info("-------开始StringRedisTemplate字符串信息读取测试-------");
        final String key = "redis:three";
        final String content = "StringRedisTemplate字符串信息";
        //Redis String通用操作组件
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        //将字符串信息写入缓存
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
        list.add(objectMapper.writeValueAsString(new User(2,"齐天大圣","jack")));
        list.add(objectMapper.writeValueAsString(new User(3,"盘古","Lee")));
        log.info("构造已经排好序的用户对象列表：{}",list);
        final String key = "redis:test:2";
        ListOperations listOperations = redisTemplate.opsForList();
        //将列表数据存储至Redis的List中
        for(String u:list){
            //往列表中添加数据-从队尾添加 先进先出
            listOperations.leftPush(key,u);
        }

        //获取Redis中List的数据--从队头中遍历获取，知道没有元素为止，读取一个元素删除掉这个元素
        log.info("-----获取Redis中List的数据-从队头中获取---");
        Object res = null;
        res = listOperations.rightPop(key);  //rightPop 执行1次取1次同时删除这个
        while (res != null){
            User user = objectMapper.readValue(res.toString(),User.class);
            log.info("当前数据：{}",user);
            res = listOperations.rightPop(key);
        }
    }

    //集合类型
    @Test
    public void set() throws Exception{
        //构造一组用户姓名列表
        List<String> userList = new ArrayList<>();
        userList.add("stackoverflow");
        userList.add("jack");
        userList.add("修罗");
        userList.add("大圣");
        userList.add("stackoverflow");
        userList.add("jack");
        userList.add("steadyheart");
        userList.add("修罗");
        userList.add("大圣");
        log.info("待处理的用户姓名列表：{}",userList);
        //遍历访问，剔除相同姓名的用户并塞入集合中，最终存入缓存中
        SetOperations setOperations = redisTemplate.opsForSet();
        final String key = "redis:test:3";
        for(String str:userList){
            setOperations.add(key,str);
        }
        //从缓存中获取用户对象集合
       /* Object res = setOperations.pop(key);
        while (res != null){
            log.info("从缓存中获取的用户集合-当前用户：{}",res);
            res = setOperations.pop(key);
        }*/
    }

    //hash存储类型
    @Test
    public void hash() throws Exception{
        //Map<K,V>.entiry
        //水果（Fruit）：(apple,苹果)、(orange,橘子)、(banana,香蕉) 加入到List里      key:redis:test:5
        //学生（Student）：(10010,姓名1） (10011,修罗） 加入到list里     key:redis:test:6
        //构造学生对象列表和水果对象列表
        List<Student> students = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();

        //往学生集合中添加学生对象
        students.add(new Student("10010","stackoverflow","悟空"));
        students.add(new Student("10012","jack","修罗"));
        students.add(new Student("10013","sam","上古"));

        //往水果集合中添加水果对象
        fruits.add(new Fruit("apple","红色"));
        fruits.add(new Fruit("orange","橙色"));
        fruits.add(new Fruit("banana","黄色"));

        final String sKey = "redis:test:5";
        final String fKey = "redis:test:6";

        //获取Hash存储操作接口HashOperations
        HashOperations hashOperations = redisTemplate.opsForHash();

        //分别遍历不同的对象列表，并采用Hash存储至缓存中
        for(Student s:students){
            //第一个参数：redis的key 第二个参数：Map.Entiry.Key  第三个参数：Map.Entity.Value
            hashOperations.put(sKey,s.getId(),s);
        }
        for(Fruit f:fruits){
            hashOperations.put(fKey,f.getName(),f);
        }

        //从缓存中获取学生对象列表与水果对象列表 获取所有
        Map<String,Student> sMap = hashOperations.entries(sKey);
        log.info("获取学生对象列表：{}",sMap);
        Map<String,Fruit> fMap = hashOperations.entries(fKey);
        log.info("获取水果对象列表：{}",fMap);

        //获取指定的学生对象
        String sField = "10012";
        Student s = (Student)hashOperations.get(sKey,sField);
        log.info("获取指定的学生对象：{} -> {}",sField,s);

        //获取指定的水果对象
        String fField = "orange";
        Fruit f = (Fruit)hashOperations.get(fKey,fField);
        log.info("获取指定的水果对象：{} -> {}",fField,f);
    }



}
