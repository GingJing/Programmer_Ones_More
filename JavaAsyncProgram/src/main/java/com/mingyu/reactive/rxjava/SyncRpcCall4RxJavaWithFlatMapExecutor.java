package com.mingyu.reactive.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.omg.PortableServer.POA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步的rxjava操作，使用自定义线程池
 *
 * @date: 2020/9/24 8:52
 * @author: GingJingDM
 * @version: 1.0
 */
public class SyncRpcCall4RxJavaWithFlatMapExecutor {

    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            10, 10, 1,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static String rpcCall(String ip, String param) {
        System.out.println(Thread.currentThread().getName() + " " + ip + " rpcCall: " + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public static void main(String[] args) throws InterruptedException {
        // ip列表
        List<String> ipList = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            ipList.add("192.168.0." + i);
        }

        // 顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .flatMap(ip -> Flowable.just(ip).subscribeOn(Schedulers.from(POOL_EXECUTOR)).map(v -> rpcCall(v, v)))
                .blockingSubscribe(System.out::println);


        System.out.println("cost: " + (System.currentTimeMillis() - start));
        System.out.println("main thread execute over and wait");
        // 挂起main线程，防止deamon线程提前终止
//        Thread.currentThread().join();
        POOL_EXECUTOR.shutdown();
    }
}
