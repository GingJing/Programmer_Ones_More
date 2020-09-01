package com.mingyu.framework.process;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * JSON对象解析
 *
 * @date: 2020/8/21 8:26
 * @author: GingJingDM
 * @version: 1.0
 */
public class PrintViewHandlerImpl implements ViewHandler {
    @Override
    public void print(HttpServletResponse response, Object result) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
