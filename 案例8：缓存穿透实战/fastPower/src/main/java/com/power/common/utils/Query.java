package com.power.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T> extends LinkedHashMap<String,Object> {
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(Map<String,Object> params){
        this.putAll(params);
        //分页参数
        if(params.get("page") != null){
            currPage = Integer.parseInt((String)params.get("page"));
        }
        if(params.get("limit") != null){
            limit = Integer.parseInt((String)params.get("limit"));
        }
        this.put("offset",(currPage - 1) * limit);
        this.put("page",currPage);
        this.put("limit",limit);

        //防止SQL注入
        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("sidx",sidx);
        this.put("order",order);

        //mybatis-plus分页
        this.page = new Page<>(currPage,limit);
        //排序
        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }
    }
}
