package com.mingyu.completablefuture.compose;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * testCombine测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 13时02分
 * @version: 1.0
 */
public class ThenCombineTest {
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
                .thenCombine(doSomethingTwo("ming"), (one, two) -> one + two);
        System.out.println(compose.get());
    }
}
