package com.power.modules.goods.controller;


import com.power.common.utils.R;
import com.power.modules.goods.service.CachePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于接收 前端用户 获取热销商品信息的访问请求 解决测试缓存穿透
 */
@RestController
public class CachePassController {

    private static final String prefix = "cache/pass";

    //自动注入缓存穿透服务处理类
    @Autowired
    private CachePassService cachePassService;

    @RequestMapping(value = prefix + "/item/info",method = RequestMethod.GET)
    public R getItem(@RequestParam String itemCode){
        try{
            //调用缓存穿透处理服务类得到返回结果
            return R.ok().put("data",cachePassService.getItemInfo(itemCode));
        }catch (Exception e){
            return R.error(e.getMessage());
        }
    }

}
