package com.mingyu.framework.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图渲染接口
 *
 * @date: 2020/8/21 8:30
 * @author: GingJingDM
 * @version: 1.0
 */
public interface View {

    /**
     * 视图渲染接口
     * @param request   请求
     * @param response  响应
     * @param result    结果
     */
    void render(HttpServletRequest request, HttpServletResponse response, Object result);
}
