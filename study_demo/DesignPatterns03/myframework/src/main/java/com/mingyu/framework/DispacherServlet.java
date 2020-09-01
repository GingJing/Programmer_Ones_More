package com.mingyu.framework;

import com.mingyu.framework.process.View;
import com.mingyu.framework.process.ViewAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 分发器
 *
 * @date: 2020/8/21 8:36
 * @author: GingJingDM
 * @version: 1.0
 */
public class DispacherServlet extends BaseInit {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uri = req.getRequestURI();
            Object result = invoke(uri);
            if (result != null) {
                // 执行渲染
                View view = new ViewAdapter();
                view.render(req, resp, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object invoke(String uri) throws Exception {
        // 获取方法
        Method method = methods.get(uri);
        if (method == null) {
            return null;
        }
        // 执行反射调用
        Class<?> declaringClass = method.getDeclaringClass();
        return method.invoke(declaringClass.newInstance());
    }
}
