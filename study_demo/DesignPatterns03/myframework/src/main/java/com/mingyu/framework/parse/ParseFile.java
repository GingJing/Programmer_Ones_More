package com.mingyu.framework.parse;

import com.mingyu.framework.BaseInit;

import java.io.InputStream;

/**
 * 文件解析接口
 *
 * @date: 2020/8/21 8:36
 * @author: GingJingDM
 * @version: 1.0
 */
public abstract class ParseFile {
    //监听的对象
    private BaseInit baseInit;
    //执行通知
    public abstract void load(InputStream is);
}