package com.power.redis;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String name;
    public User(){}
    public User(Integer id,String userName,String name){
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}
