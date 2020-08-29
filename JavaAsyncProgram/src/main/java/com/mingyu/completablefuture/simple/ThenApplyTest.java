package com.mingyu.completablefuture.simple;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.*;

/**
 * thenApply测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 12时17分
 * @version: 1.0
 */
public class ThenApplyTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return "one";
        });

        CompletableFuture<String> two = one.thenApply(r -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return r + " two got you";
        });

        CompletableFuture<String> three = one.thenApplyAsync(r -> {
//        CompletableFuture<String> three = two.thenApplyAsync(r -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return r + " three got you";
        });

        CompletableFuture<String> four = one.thenApplyAsync(r -> {
//        CompletableFuture<String> four = three.thenApplyAsync(r -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return r + " four got you";
        },poolExecutor);

        System.out.println(one.get());
        System.out.println(System.currentTimeMillis() - start);
        long oneGet = System.currentTimeMillis();
        System.out.println(two.get());
        long twoGet = System.currentTimeMillis();
        System.out.println(twoGet - oneGet);
        System.out.println(three.get());
        long threeGet = System.currentTimeMillis();
        System.out.println(threeGet - twoGet);
        System.out.println(four.get());
        System.out.println(System.currentTimeMillis() - threeGet);
    }

}
