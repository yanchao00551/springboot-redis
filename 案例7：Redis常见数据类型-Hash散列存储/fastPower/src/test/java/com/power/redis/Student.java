package com.power.redis;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

//学生对象
@Data
@ToString
public class Student implements Serializable {
    private String id;
    private String userName;
    private String name;

    //无参构造
    public Student(){}

    //所有参数的构造方法
    public Student(String id,String userName,String name){
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}
