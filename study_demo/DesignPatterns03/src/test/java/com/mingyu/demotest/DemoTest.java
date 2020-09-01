package com.mingyu.demotest;

import com.mingyu.adapter.PunchCard;
import com.mingyu.adapter.PunchCardBefore;
import com.mingyu.aop.service.UserService;
import com.mingyu.decorator.*;
import com.mingyu.factory.Product;
import com.mingyu.factory.ProductFactory;
import com.mingyu.flyweight.Flyweight;
import com.mingyu.flyweight.FlyweightFactory;
import com.mingyu.flyweight.UnsharedConcreteFlyweight;
import com.mingyu.pepole.DayTime;
import com.mingyu.pepole.Night;
import com.mingyu.pepole.People;
import com.mingyu.pepole.State;
import com.mingyu.proxy.cglib.SFangProxy;
import com.mingyu.proxy.jdk.LandLord;
import com.mingyu.proxy.jdk.LandLordService;
import com.mingyu.proxy.jdk.QFangProxy;
import com.mingyu.singleton.SingletonModel;
import com.mingyu.state.Context;
import com.mingyu.state.StartState;
import com.mingyu.state.StopState;
import com.mingyu.strategy.OrderMoney;
import com.mingyu.strategy.Strategy;
import com.mingyu.strategy.StrategyFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.lang.reflect.Proxy;

/**
 * Demo测试
 *
 * @date: 2020/8/19 8:43
 * @author: GingJingDM
 * @version: 1.0
 */
public class DemoTest {

    @Test
    public void testHungrySingleton() {
        SingletonModel singletonModel1 = SingletonModel.getInstance();
        SingletonModel singletonModel2 = SingletonModel.getInstance();

        singletonModel1.sendMessage();
        singletonModel2.sendMessage();

        System.out.println(singletonModel1 == singletonModel2);
    }

    @Test
    public void testProxy() {
        LandLordService landLord1 = new LandLord();
        QFangProxy qFangProxy = new QFangProxy(landLord1);
        LandLordService jdkProxy = (LandLordService) Proxy.newProxyInstance(
                LandLordService.class.getClassLoader(),
                new Class[]{LandLordService.class},
                qFangProxy
        );
        jdkProxy.rentingPay("张三");

        com.mingyu.proxy.cglib.LandLordService landLord2 = new com.mingyu.proxy.cglib.LandLord();
        SFangProxy sFangProxy = new SFangProxy(landLord2);
        com.mingyu.proxy.cglib.LandLordService cglibProxy = (com.mingyu.proxy.cglib.LandLordService) Enhancer
                .create(com.mingyu.proxy.cglib.LandLordService.class, sFangProxy);
        cglibProxy.rentingPay("李四");
    }

    @Test
    public void testFactory() {
        ProductFactory factory = new ProductFactory();
        Product mobile = factory.info("mobile");
        mobile.show();
        Product car = factory.info("car");
        car.show();
    }

    @Test
    public void testBeanFactory() {
        Resource resource = new ClassPathResource("spring-factory.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);

        Product car = (Product) beanFactory.getBean("car");
        car.show();
    }

    @Test
    public void testAdapter() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-adapter.xml");
        PunchCard punchCard = (PunchCard) applicationContext.getBean("proxyFactoryBean");
        punchCard.info("王五");
    }

    @Test
    public void testAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }

    @Test
    public void testDecorator() {
        // 被扩展的对象
        Tea iceTea = new IceTea();

        // 创建扩展对象
        DecoratorTea peachTea = new PeachTea();
        peachTea.setTea(iceTea);

        DecoratorTea cocoTea = new CocoTea();
        cocoTea.setTea(peachTea);
        cocoTea.making();
    }

    @Test
    public void testFlyweight() {
        int extrinsic = 22;

        Flyweight flyweightX = FlyweightFactory.getFlyweight("X");
        flyweightX.operate(extrinsic);
        flyweightX.setIntrinsic("X-Noo1");
        System.out.println("Intrinsic:"+flyweightX.getIntrinsic());

        Flyweight flyweightY = FlyweightFactory.getFlyweight("Y");
        flyweightY.operate(extrinsic);

        Flyweight flyweightZ = FlyweightFactory.getFlyweight("Z");
        flyweightZ.operate(extrinsic);

        Flyweight flyweightReX = FlyweightFactory.getFlyweight("X");
        flyweightReX.operate(extrinsic);
        System.out.println("Intrinsic:"+flyweightReX.getIntrinsic());

        Flyweight unsharedFlyweight = new UnsharedConcreteFlyweight("X");
        unsharedFlyweight.operate(extrinsic);
    }

    @Test
    public void testPeopleState() {
        //创建一个对象
        People people = new People();

        //创建一个状态
        State dayTime = new DayTime();
        //改变对象的状态
        dayTime.change(people,dayTime);
        //执行相关行为
        people.getState().execute();

        //Night
        State night = new Night();
        night.change(people,night);

        //执行相关行为
        people.getState().execute();
    }

    @Test
    public void testContextState() {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }

    @Test
    public void testStrategy() {
        //创建对象实例
        OrderMoney orderMoney = new OrderMoney();
        //获取指定策略
        Strategy strategy = StrategyFactory.get(3);
        //设置策略
        orderMoney.setStrategy(strategy);
        //执行计算[基于不同策略计算]
        Integer money = orderMoney.moneySum(1000);
        System.out.println(money);

    }

}
