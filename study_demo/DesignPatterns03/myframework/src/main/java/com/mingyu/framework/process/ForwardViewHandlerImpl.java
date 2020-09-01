package com.mingyu.framework.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 转发解析对象
 *
 * @date: 2020/8/21 8:29
 * @author: GingJingDM
 * @version: 1.0
 */
public class ForwardViewHandlerImpl implements ViewHandler {
    @Override
    public void forward(HttpServletRequest request, HttpServletResponse response, Object result) {
        try {
            request.getRequestDispatcher(result.toString()).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
