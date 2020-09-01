package com.mingyu.framework.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XmlBean
 *
 * @date: 2020/8/21 12:26
 * @author: GingJingDM
 * @version: 1.0
 */
public class XmlBean {

    /** 文档对象 */
    private static Document document;

    /** 解析的数据 */
    private static List<MapNode> mapNodeList;

    /**
     * 解析before节点
     *
     * @return before节点映射
     * @throws Exception
     */
    public static Map<String, String> before() throws Exception {
        List<Element> packageList = document.selectNodes("//before");
        if (packageList != null && packageList.size() > 0) {
            Element element = packageList.get(0);
            Map<String, String> aopMap = new HashMap<>();
            aopMap.put("package", element.attributeValue("package"));
            aopMap.put("ref", element.attributeValue("ref"));
            aopMap.put("method", element.attributeValue("method"));
            return aopMap;
        }
        return null;
    }

    /**
     * 解析base-package节点
     *
     * @return base-package的值
     * @throws Exception
     */
    public static String scanner() throws Exception {
        if (document == null) {
            return "com.mingyu.controller";
        }
        List<Element> packageList = document.selectNodes("//component-scan");
        if (packageList == null || packageList.size() == 0) {
            return "com.mingyu.controller";
        }
        Element element = packageList.get(0);
        return element.attributeValue("base-package");
    }

    /**
     * 加载配置文件，并解析Bean
     *
     * @param is          配置文件输入流
     * @throws Exception
     */
    public static void load(InputStream is) throws Exception {
        // 解析xml，初始化beans集合
        // 1. 创建SaxReader对象
        SAXReader saxReader = new SAXReader();
        // 2. 读取配置文件 获得document对象
        Document document = saxReader.read(is);
        XmlBean.document = document;
    }

    /**
     * 创建文档中所有对象的实例
     *
     * @return 文档中所有对象实例映射
     */
    public static Map<String, Object> initBeans() {
        return newInstance(getMapNodeList());
    }

    /**
     * 解析所有MapNode节点
     *
     * @return 所有MapNode节点
     */
    public static List<MapNode> getMapNodeList() {
        if (XmlBean.mapNodeList != null && XmlBean.mapNodeList.size() > 0) {
            return XmlBean.mapNodeList;
        }
        // 获得bean标签集合
        List<Element> beanList = document.selectNodes("//bean");
        List<MapNode> mapNodeList = new ArrayList<>();
        for (Element element : beanList) {
            MapNode mapNode = new MapNode();
            mapNode.setId(element.attributeValue("id"));
            mapNode.setClassname(element.attributeValue("class"));

            List<Element> property = element.elements("property");
            if (property != null && !property.isEmpty()) {
                Map<String, String> propertyMap = new HashMap<>();
                for (Element element1 : property) {
                    propertyMap.put(element1.attributeValue("name"), element1.attributeValue("ref"));
                }
                mapNode.setPropertyMap(propertyMap);
            }
            System.out.println(mapNode);
            mapNodeList.add(mapNode);
        }
        XmlBean.mapNodeList = mapNodeList;
        return XmlBean.mapNodeList;
    }

    /**
     * 实例化对象，返回bean id与实例对象 映射
     *
     * @param mapNodeList MapNode集合，即数据集合
     * @return 映射对象
     */
    public static Map<String, Object> newInstance(List<MapNode> mapNodeList) {
        if (mapNodeList == null || mapNodeList.size() == 0) {
            return null;
        }
        try {
            // 用来装载所有实例的集合
            Map<String, Object> beans = new HashMap<>();
            // 第1次实例化： 将每个对象都创建一个实例
            for (MapNode mapNode : mapNodeList) {
                String id = mapNode.getId();
                String classname = mapNode.getClassname();
                beans.put(id, Class.forName(classname).newInstance());
            }

            // 第2次没有依赖注入的筛选
            Map<String, Object> realBeans = new HashMap<>();
            for (MapNode mapNode : mapNodeList) {
                if (mapNode.getPropertyMap() == null || mapNode.getPropertyMap().size() == 0) {
                    realBeans.put(mapNode.getId(), beans.get(mapNode.getId()));
                    beans.remove(mapNode.getId());
                }
            }

            // 第3次依赖注入实现
            int size = beans.size();
            while (size > 0) {
                // 在realBeans中有的对象实现依赖注入
                for (MapNode mapNode : mapNodeList) {
                    Object o = beans.get(mapNode.getId());
                    if (o != null) {
                        for (Map.Entry<String, String> en : mapNode.getPropertyMap().entrySet()) {
                            String name = en.getKey();
                            String ref = en.getValue();
                            // 如果ref在realbeans中有，则获取出来，并赋值
                            Object refo = realBeans.get(ref);
                            if (refo != null) {
                                Field field = o.getClass().getDeclaredField(name);
                                field.setAccessible(true);
                                field.set(o, refo);
                                beans.remove(mapNode.getId());
                                realBeans.put(mapNode.getId(), o);
                            }
                        }
                    }
                }
                size = beans.size();
            }
            return realBeans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象替换成代理对象
     * @param beans             id与实例的映射
     * @param id                指定的id
     * @param proxyInstance     代理实例对象
     * @return  null
     * @throws Exception
     */
    public static Object replaceProxy(Map<String, Object> beans, String id, Object proxyInstance) throws Exception {
        // 将实例中所有的引用对象替换成代理
        List<MapNode> mapNodeList = XmlBean.getMapNodeList();
        for (MapNode mapNode : mapNodeList) {
            Map<String, String> propertyMap = mapNode.getPropertyMap();
            if (propertyMap != null && propertyMap.size() > 0) {
                for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                    String name = entry.getKey();
                    String ref = entry.getValue();
                    if (ref.equals(id)) {
                        Object o = beans.get(mapNode.getId());
                        Field field = o.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        field.set(o, proxyInstance);
                        beans.put(mapNode.getId(), o);
                    }
                }
            }
        }
        return null;
    }
}
