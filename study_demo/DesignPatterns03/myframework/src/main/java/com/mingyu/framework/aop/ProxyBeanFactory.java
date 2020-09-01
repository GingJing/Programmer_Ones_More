package com.mingyu.framework.aop;

import com.mingyu.framework.util.XmlBean;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 代理类
 *
 * @author: jmm
 * @date: 2020/8/23
 */
public class ProxyBeanFactory {

    /**
     * 增强操作
     *
     * @param beans bean id->instance
     */
    public static void proxy(Map<String, Object> beans) {
        try {
            //获取对应前置增强节点信息
            Map<String, String> map = XmlBean.before();
            if (map == null) {
                return;
            }
            //获取指定的包
            String packageinfo = map.get("package");
            //获取增强类实例
            Object aopInstance = beans.get(map.get("ref"));
            //增强方法
            Method method = aopInstance.getClass().getDeclaredMethod(map.get("method"));
            //增强操作
            beforeHandler(beans, aopInstance, method, packageinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行增强
     *
     * @param beans          bean集合
     * @param aopInstance    增强类实例
     * @param method         增强方法
     * @param packageinfo    包
     * @author: jmm
     * @date: 2020/8/23
     */
    private static void beforeHandler(Map<String, Object> beans, Object aopInstance, Method
            method, String packageinfo) throws Exception {
        //循环增强
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            //当前实例的包
            String entryPackage = entry.getValue().getClass().getPackage().getName();
            //属于增强包下的类，需要增强
            if (entryPackage.equals(packageinfo)) {
                //创建代理
                Object proxy = Proxy.newProxyInstance(
                        entry.getValue().getClass().getClassLoader(),
                        entry.getValue().getClass().getInterfaces(),
                        new BeforeProxyBean(entry.getValue(), aopInstance, method)
                );
                //将非代理对象替换掉
                beans.put(entry.getKey(), proxy);
                //将实例对象中对应属性替换成代理对象
                XmlBean.replaceProxy(beans, entry.getKey(), proxy);
            }
        }
    }
}