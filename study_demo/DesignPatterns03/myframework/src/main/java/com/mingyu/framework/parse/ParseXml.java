package com.mingyu.framework.parse;

import java.io.InputStream;

/**
 * @date: 2020/8/23 15:21
 * @author: GingJingDM
 * @version: 1.0
 */
public class ParseXml extends ParseFile {
    @Override
    public void load(InputStream is) {
        System.out.println("开始加载xml文件");
    }
}
