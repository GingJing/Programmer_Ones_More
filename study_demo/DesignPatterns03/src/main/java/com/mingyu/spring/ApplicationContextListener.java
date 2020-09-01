package com.mingyu.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring应用监听器
 *
 * @date: 2020/8/19 8:56
 * @author: GingJingDM
 * @version: 1.0
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("ContextRefreshedEvent事件，容器内发生变更");
    }
}
