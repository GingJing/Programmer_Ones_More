package com.mingyu.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 消息通知器
 *
 * @date: 2020/8/19 9:03
 * @author: GingJingDM
 * @version: 1.0
 */
public class MessageNotifier implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("事件对象：" + applicationEvent.getClass().getName());
    }
}
