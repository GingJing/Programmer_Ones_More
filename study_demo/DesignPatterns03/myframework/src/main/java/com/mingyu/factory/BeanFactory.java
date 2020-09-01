package com.mingyu.factory;

/**
 * bean工厂
 *
 * @date: 2020/8/23 15:26
 * @author: GingJingDM
 * @version: 1.0
 */
public interface BeanFactory {

    /**
     * 通过id获取实例
     *
     * @param id  bean id
     * @return    bean实例对象或代理对象
     * @throws Exception
     */
    Object getBean(String id) throws Exception;

    /**
     * 通过请求uri获取实例
     *
     * @param url 请求uri
     * @return bean实例对象或代理对象
     * @throws Exception
     */
    Object getUrlBean(String url) throws Exception;
}
