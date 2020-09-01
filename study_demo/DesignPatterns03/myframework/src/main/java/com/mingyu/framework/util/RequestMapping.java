package com.mingyu.framework.util;

import java.lang.annotation.*;

/**
 * 请求映射注解
 *
 * @date: 2020/8/21 14:35
 * @author: GingJingDM
 * @version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    // 定义一个注解的属性，属性名字叫value
    String value() default "";
}
