package com.power.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value=RetentionPolicy.RUNTIME)  //声明注解的保留期限
@Target(value=ElementType.METHOD)  //声明注解可以使用的目标类型
public @interface NeedTest {
    String value() default "";  //声明注解成员
}
