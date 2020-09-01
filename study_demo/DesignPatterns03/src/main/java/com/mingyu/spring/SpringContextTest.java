package com.mingyu.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring监听器测试
 *
 * @date: 2020/8/19 9:00
 * @author: GingJingDM
 * @version: 1.0
 */
public class SpringContextTest {

    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("spring-event.xml");
        MessageEvent messageEvent = new MessageEvent("beijing","13670000000","hello!");
        act.publishEvent(messageEvent);
    }
}
