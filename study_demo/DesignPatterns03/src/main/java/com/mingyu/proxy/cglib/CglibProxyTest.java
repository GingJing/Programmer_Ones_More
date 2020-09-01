package com.mingyu.proxy.cglib;

import com.mingyu.proxy.cglib.LandLord;
import com.mingyu.proxy.cglib.LandLordService;
import com.mingyu.proxy.jdk.QFangProxy;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * JDK代理测试
 *
 * @date: 2020/8/19 18:04
 * @author: GingJingDM
 * @version: 1.0
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        LandLordService landLordService = new LandLord();
        SFangProxy qFang = new SFangProxy(landLordService);
        LandLordService landLordServiceProxy = (LandLordService) Enhancer.create(LandLordService.class, qFang);
        landLordServiceProxy.rentingPay("王五");
    }
}
