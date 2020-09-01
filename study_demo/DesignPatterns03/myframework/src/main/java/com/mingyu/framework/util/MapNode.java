package com.mingyu.framework.util;

import java.util.Map;

/**
 * 节点映射
 *
 * @date: 2020/8/21 12:29
 * @author: GingJingDM
 * @version: 1.0
 */
public class MapNode {
    /** id */
    private String id;

    /** classname */
    private String classname;

    private Map<String, String> propertyMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        if (this.propertyMap == null) {
            this.propertyMap = propertyMap;
        }
        this.propertyMap.putAll(propertyMap);
    }
}
