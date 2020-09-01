package com.mingyu.factory;

import com.mingyu.framework.aop.ProxyBeanFactory;
import com.mingyu.framework.util.ParseAnnotation;
import com.mingyu.framework.util.XmlBean;
import com.mingyu.service.AccountService;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @date: 2020/8/23 15:29
 * @author: GingJingDM
 * @version: 1.0
 */
public class XmlBeanFactoryImpl implements BeanFactory {

    // 存储了对应的实例对象
    // spring.xml bean id
    // spring.xml bean class->instance
    private static Map<String,Object> beans;

    //所有方法
    public static Map<String, Method> methods;

    //Map uri->id
    private static Map<String,String> urlIdMaps;

    public XmlBeanFactoryImpl() {
        instance();
    }

    public XmlBeanFactoryImpl(String conf) {
        try {
            InputStream is = XmlBeanFactoryImpl.class.getClassLoader().getResourceAsStream(conf);
            XmlBean.load(is);
            instance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void instance() {
        try {
            // 1.创建对应的实例
            beans = XmlBean.initBeans();
            // 增强操作
            ProxyBeanFactory.proxy(beans);
            // 2.解析所有controller里面拥有@RequestMapping注解的对象，并存储到Map<String,Method> methods
            methods = ParseAnnotation.parseRequestMapping();
            // 3.解析uri和id的映射关系
            urlIdMaps = ParseAnnotation.parseUrlMappingInstance(methods, beans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) throws Exception {
        return beans.get(id);
    }

    @Override
    public Object getUrlBean(String url) throws Exception {
        String id = urlIdMaps.get(url);
        if (StringUtils.isNotEmpty(id)) {
            return beans.get(id);
        }
        return null;
    }

    public static Map<String, Method> getMethods() {
        return methods;
    }

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new XmlBeanFactoryImpl("spring.xml");
        AccountService accountService = (AccountService) beanFactory.getBean("accountService");
        System.out.println(accountService);
        accountService.one();
    }
}
