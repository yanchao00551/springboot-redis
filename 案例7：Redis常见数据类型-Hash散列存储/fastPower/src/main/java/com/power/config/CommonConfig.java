package com.power.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//通用化配置
@Configuration
public class CommonConfig {
    //Redis链接工厂
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 1、链接工厂能连接上Redis服务器
     * 2、配置Key-Value序列化机制
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        //定义RedisTemplate实例
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //设置Redis的链接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //TODO: 指定Key序列化策略为String序列化，Value为JDK自带的序列化策略
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //如果用String  Redis客户端查看数据不乱码，存和查多个序列化和反序列化步骤。
        //如果用jdk序列化，Redis客户端查看数据乱码 ，可以直接存和查 ，不需要序列化和反序列化
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //redisTemplate.setValueSerializer(new StringRedisSerializer());

        //TODO: 指定hashKey序列化策略为String序列化-针对Hash散列存储
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    /**
     * 1、链接工厂能连接上Redis服务器
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        //采用默认配置即可-后续有自定义配置时则在此处添加即可
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }


}
