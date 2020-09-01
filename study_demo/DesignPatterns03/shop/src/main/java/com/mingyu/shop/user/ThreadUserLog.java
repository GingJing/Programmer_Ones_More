package com.mingyu.shop.user;

import org.springframework.stereotype.Component;

/**
 * 用户日志线程本地对象
 *
 * @date: 2020/8/27 8:48
 * @author: GingJingDM
 * @version: 1.0
 */
@Component
public class ThreadUserLog {

    /** 存储线程对应的用户名日志信息 */
    private static ThreadLocal<AbstractLogComponent> userRecode = new ThreadLocal<>();

    /**
     * 添加用户记录
     *
     * @param logComponent 日志组件
     */
    public void add(AbstractLogComponent logComponent){
        userRecode.set(logComponent);
    }

    /**
     * 记录方法名和参数
     *
     * @param args 参数数组 第一个元素为方法名，第二个为日志信息
     * @return 返回日志记录
     */
    public String reload(String... args){
        AbstractLogComponent logComponent = userRecode.get();
        logComponent.supplementLogContent(args);
        return logComponent.toString();
    }

    /**
     * 获取日志组件
     *
     * @return 日志组件
     */
    public AbstractLogComponent get() {
        return userRecode.get();
    }

    /**
     * 移除
     */
    public void remove(){
        userRecode.remove();
    }
}
