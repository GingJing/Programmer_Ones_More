package com.mingyu.framework.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析RequestMapping注解工具类
 *
 * @date: 2020/8/21 12:23
 * @author: GingJingDM
 * @version: 1.0
 */
public class ParseAnnotation {
    /**
     * 解析所有RequestMapping注解
     *
     * @return 每个注解对应的方法
     * @throws Exception
     */
    public static Map<String, Method> parseRequestMapping() throws Exception {
        // 获取解析包
        String packageName = XmlBean.scanner();
        List<Class<?>> classes = LoadClass.getClasses(packageName);
        return parseMapping(classes);
    }

    /**
     * 获取对应路径和对应id映射关系
     *
     * @param methodMap 路径与方法的映射
     * @param beans     id与实例化对象的映射
     * @return 对应路径和对应id的映射关系
     */
    public static Map<String, String> parseUrlMappingInstance(Map<String, Method> methodMap, Map<String, Object> beans) {
        // url -> id
        Map<String, String> urlInstanceMap = new HashMap<>();

        // 循环所有方法
        for (Map.Entry<String, Method> methodEntry : methodMap.entrySet()) {
            String url = methodEntry.getKey();
            Class<?> clazz = methodEntry.getValue().getDeclaringClass();
            // 循环所有实例
            for (Map.Entry<String, Object> instanceEntry : beans.entrySet()) {
                if (instanceEntry.getValue().getClass() == clazz) {
                    // url -> id
                    urlInstanceMap.put(url, instanceEntry.getKey());
                    break;
                }
            }
        }
        return urlInstanceMap;
    }

    /**
     * 每个注解对应的方法
     *
     * @param classes 类集合
     * @return
     */
    public static Map<String, Method> parseMapping(List<Class<?>> classes) {
        Map<String, Method> handlers = new HashMap<>();
        for (Class<?> aClass : classes) {
            // 获取类上的注解
            RequestMapping typeAnnotation = aClass.getAnnotation(RequestMapping.class);
            String typeAnnotationName = "";
            if (typeAnnotation != null) {
                typeAnnotationName = typeAnnotation.value();
            }
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String value = methodAnnotation.value();
                    System.out.println(typeAnnotationName + value
                            + "                    "
                            + method.getDeclaringClass().getName() + "." + method.getName());
                    handlers.put(typeAnnotationName + value, method);
                }
            }
        }
        return handlers;
    }
}
