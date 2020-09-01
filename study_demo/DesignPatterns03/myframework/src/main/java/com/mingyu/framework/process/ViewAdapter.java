package com.mingyu.framework.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图渲染适配器
 *
 * @date: 2020/8/21 8:32
 * @author: GingJingDM
 * @version: 1.0
 */
public class ViewAdapter implements View {

    private ViewHandler viewHandler;

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response, Object result) {
        if (result instanceof String) {
            viewHandler = new ForwardViewHandlerImpl();
            viewHandler.forward(request, response, result);
        } else {
            viewHandler = new PrintViewHandlerImpl();
            viewHandler.print(response, result);
        }
    }
}
