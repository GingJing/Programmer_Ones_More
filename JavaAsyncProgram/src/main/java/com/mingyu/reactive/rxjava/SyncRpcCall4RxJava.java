package com.mingyu.reactive.rxjava;

import io.reactivex.rxjava3.core.Flowable;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步的rxjava操作
 *
 * @date: 2020/9/24 8:52
 * @author: GingJingDM
 * @version: 1.0
 */
public class SyncRpcCall4RxJava {

    public static String rpcCall(String ip, String param) {
        System.out.println(Thread.currentThread().getName() + " " + ip + " rpcCall: " + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public static void main(String[] args) {
        // ip列表
        List<String> ipList = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            ipList.add("192.168.0." + i);
        }

        // 顺序调用
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);

        System.out.println("cost: " + (System.currentTimeMillis() - start));

    }
}
