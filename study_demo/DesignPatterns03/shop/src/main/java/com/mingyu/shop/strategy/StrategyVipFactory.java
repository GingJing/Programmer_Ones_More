package com.mingyu.shop.strategy;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * vip策略工厂
 *
 * @date: 2020/8/28 9:30
 * @author: GingJingDM
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "strategy")
public class StrategyVipFactory implements ApplicationContextAware {

    private ApplicationContext act;

    //定义一个Map，存储等级和策略的关系,通过application.yml配置注入进来
    private Map<Integer,String> strategyMap;

    /**
     * 根据等级获取vip策略
     *
     * @param level 会员等级
     * @return vip策略
     */
    public StrategyVip getVip(Integer level) {
        String id = strategyMap.get(level);
        return act.getBean(id, StrategyVip.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = applicationContext;
    }
}
