package com.power.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
