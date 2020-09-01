package com.mingyu.shop.aop;

import com.mingyu.shop.user.ThreadUserLog;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 *
 * @date: 2020/8/27 9:11
 * @author: GingJingDM
 * @version: 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private ThreadUserLog threadUserLog;

    @SneakyThrows
    @Before("execution(* com.mingyu.shop.service.impl.*.*(..))")
    public void logRecord(JoinPoint joinPoint) {
        //获取方法名字和参数
        String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        //记录日志
        log.info(threadUserLog.reload(methodName, args(joinPoint.getArgs())));
    }

    /**
     * 获取参数
     * @param args 拦截参数
     * @return 组装后的参数
     */
    public String args(Object[] args){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i <args.length ; i++) {
            buffer.append(" args("+i+"):"+args[i].toString());
        }
        return buffer.toString();
    }
}
