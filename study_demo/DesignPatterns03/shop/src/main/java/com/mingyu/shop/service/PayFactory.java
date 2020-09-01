package com.mingyu.shop.service;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 支付工厂
 *
 * @date: 2020/9/1 12:33
 * @author: GingJingDM
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(value = "pey")
public class PayFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, String> paymap;


    public PayChannel createChannel(String key) {
        return applicationContext.getBean(paymap.get(key), PayChannel.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
