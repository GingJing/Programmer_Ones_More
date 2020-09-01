package com.mingyu.framework.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图处理器
 *
 * @date: 2020/8/21 8:23
 * @author: GingJingDM
 * @version: 1.0
 */
public interface ViewHandler {

    /**
     * 输出字符串
     *
     * @param response 响应
     * @param result  返回结果
     */
    default void print(HttpServletResponse response, Object result){}

    /**
     * forward转发
     *
     * @param request   请求
     * @param response  响应
     * @param result    返回结果
     */
    default void forward(HttpServletRequest request, HttpServletResponse response, Object result){}
}
