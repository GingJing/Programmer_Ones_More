package com.mingyu.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * JDK代理测试
 *
 * @date: 2020/8/19 18:04
 * @author: GingJingDM
 * @version: 1.0
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        LandLordService landLordService = new LandLord();
        QFangProxy qFang = new QFangProxy(landLordService);
        LandLordService landLordServiceProxy = (LandLordService) Proxy.newProxyInstance(
                        LandLordService.class.getClassLoader(),
                        new Class[]{LandLordService.class},
                        qFang);
        landLordServiceProxy.rentingPay("王五");
    }
}
