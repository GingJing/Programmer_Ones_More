package com.mingyu.completablefuture.compose;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenCompose测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 12时55分
 * @version: 1.0
 */
public class ThenComposeTest {

    public static CompletableFuture<String> doSomethingOne(String someString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // do some thing
            System.out.println(someString);
            return someString;
        });
    }

    public static CompletableFuture<String> doSomethingTwo(String someString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // do some thing
            System.out.println(someString);
            return someString + " ming";
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> compose = doSomethingOne("I get your name: ")
                .thenCompose(ThenComposeTest::doSomethingTwo);
        System.out.println(compose.get());
    }
}
