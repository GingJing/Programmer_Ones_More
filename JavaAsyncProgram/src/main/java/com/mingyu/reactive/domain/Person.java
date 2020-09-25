package com.mingyu.reactive.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 人
 *
 * @date: 2020/9/24 8:36
 * @author: GingJingDM
 * @version: 1.0
 */
@Getter
@Setter
public class Person {

    private String name;

    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
