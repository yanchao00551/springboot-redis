package com.power.modules.goods.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.power.modules.goods.dao.ItemDao;
import com.power.modules.goods.entity.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import sun.awt.SunHints;

import java.util.concurrent.TimeUnit;

//缓存穿透Service
@Service
public class CachePassService {
    private static final Logger log = LoggerFactory.getLogger(CachePassService.class);

    //注入Dao
    @Autowired
    private ItemDao itemDao;
    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //注入序列化接口
    @Autowired
    private ObjectMapper objectMapper;

    //定义缓存中Key命名前缀 前缀用数据库表名
    private static final String keyPrefix = "item";

    /**
     * 获取商品详情，如果有缓存则从缓存中获取；如果没有，则从数据库查询，并将查询结果塞入到缓存中
     * 在此情况下存在有缓存穿透
     * 解决方案：如果数据中没有，会Key设置进缓存，Value设置NULL，TTL过期时间（根据时间情况来设置，30分钟）
     */
    public ItemEntity getItemInfo(String itemCode) throws Exception{
        //定义商品对象
        ItemEntity itemEntity = null;
        //定义缓存中真正的Key：由前缀和商品编码组成
        final String key = keyPrefix + itemCode;
        //定义Redis的操作组件ValueOperations
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            log.info("----获取商品详情-缓存中存在该商品----商品编号为：{}",itemCode);
            //从缓存中查询该商品
            Object res = valueOperations.get(key);
            if(res != null && !Strings.isNullOrEmpty(res.toString())){
                //TODO: 自己的逻辑处理
                itemEntity = objectMapper.readValue(res.toString(),ItemEntity.class);
            }
        }else{
            //else 表示在缓存中没有找到该商品
            log.info("---获取商品详情-缓存中不存在该商品-从数据库中查询---商品编号为：{}",itemCode);
            //从数据库中获取该商品详情
            itemEntity = itemDao.selectByCode(itemCode);
            if(itemEntity != null){
                //如果从数据库中查得到该商品，则将序列化到缓存中
                valueOperations.set(key,objectMapper.writeValueAsString(itemEntity));
            }else{
                //缓存中没有，数据库也没有。为了避免缓存穿透
                valueOperations.set(key,"",30L, TimeUnit.MINUTES);
            }
        }
        return itemEntity;
    }

}
