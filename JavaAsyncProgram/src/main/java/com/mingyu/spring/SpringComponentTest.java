package com.mingyu.spring;

import com.mingyu.spring.async.AsyncAnnotationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring测试
 *
 * @author: GingJingDM
 * @date: 2020年 08月30日 08时57分
 * @version: 1.0
 */
public class SpringComponentTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-async-annotation.xml");
        System.out.println(Thread.currentThread().getName() + " start ");
        AsyncAnnotationTest asyncAnnotationTest = (AsyncAnnotationTest) applicationContext.getBean("asyncAnnotationTest");
        asyncAnnotationTest.printMessages();
        System.out.println(Thread.currentThread().getName() + " shutdown ");
    }
}
