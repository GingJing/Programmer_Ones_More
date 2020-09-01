package com.mingyu.framework;

import com.mingyu.framework.parse.ParseFile;
import com.mingyu.framework.parse.ParseXml;
import com.mingyu.framework.util.ParseAnnotation;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;


public class BaseInit extends HttpServlet {

    /** 需要通知的对象 */
    private ParseFile parseFile = new ParseXml();

    // 储存所有请求路径和对应的处理方法
    public static Map<String, Method> methods;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try{
            // 解析controller里面所有含有@RequestMapping注解的对象，将对应的路径和Method的关系存储到map中
            methods = ParseAnnotation.parseRequestMapping();
            System.out.println("methods: " + methods);

            // 获取当前要解析的配置文件--观察者模式
            String conf = config.getInitParameter("contextLocation");
            if (StringUtils.isNotEmpty(conf)) {
                InputStream inputStream = BaseInit.class.getClassLoader().getResourceAsStream(conf);
                parseFile.load(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
