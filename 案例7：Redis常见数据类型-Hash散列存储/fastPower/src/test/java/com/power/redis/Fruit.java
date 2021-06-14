package com.power.redis;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

//水果类
@Data
@ToString
public class Fruit implements Serializable {
    private String name;
    private String color;
    //无参构造方法
    public Fruit(){}
    public Fruit(String name,String color){
        this.name = name;
        this.color = color;
    }
}
