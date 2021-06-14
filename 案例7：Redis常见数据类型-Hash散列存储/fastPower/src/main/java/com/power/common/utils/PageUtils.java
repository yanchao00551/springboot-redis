package com.power.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<?> list;

    /**
     * 分页
     * @param list
     * @param totalCount
     * @param pageSize
     * @param currPage
     */
    public PageUtils(List<?> list,int totalCount,int pageSize,int currPage){
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public PageUtils(Page<?> page){
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize  = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = page.getPages();
    }


}
