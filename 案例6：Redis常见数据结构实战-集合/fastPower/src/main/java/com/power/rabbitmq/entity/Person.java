package com.power.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 信息实体类
 */
@Data
@ToString
public class Person implements Serializable {
    private Integer id;  //人员id
    private String name;  //人员姓名
    private String userName;  //用户名
    //默认构造
    public  Person(){

    }
    //所有参数的构造方法
    public Person(Integer id,String name,String userName){
        this.id = id;
        this.name = name;
        this.userName = userName;
    }

}
