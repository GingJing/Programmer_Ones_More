package com.mingyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user实体
 *
 * @author: GingJingDM
 * @date: 2020年 10月08日 22时54分
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
