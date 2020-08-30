package com.mingyu.completablefuture.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 流测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 20时42分
 * @version: 1.0
 */
public class StreamTest {

    public static String rpcCall(String ip, String param) {
        System.out.println(ip + " rpcCall: " + param);
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

        // 发起广播调用
        long sync = System.currentTimeMillis();
        List<String> result = new ArrayList<>();
        // 顺序调用
        for (String ip : ipList) {
            result.add(rpcCall(ip, ip));
        }

        result.forEach(System.out::println);
        System.out.println("cost: " + (System.currentTimeMillis() - sync));

        long async = System.currentTimeMillis();
        // 并发调用
        List<CompletableFuture<String>> futureList = ipList
                .stream()
                .map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip)))
                .collect(Collectors.toList());
        // 等待所有异步结果执行完毕
        List<String> list = futureList
                .stream()
                // 同步获取结果
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
        System.out.println("cost: " + (System.currentTimeMillis() - async));
    }
}
