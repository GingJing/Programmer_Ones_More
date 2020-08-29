package com.mingyu.completablefuture.compose;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * allOf测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 13时32分
 * @version: 1.0
 */
public class AnyOfTest {

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

    public static void anyOf() throws InterruptedException, ExecutionException {
        List<CompletableFuture<String>> futuresList = new ArrayList<>();
        futuresList.add(doSomethingOne("6"));
        futuresList.add(doSomethingOne("7"));
        futuresList.add(doSomethingOne("8"));
        futuresList.add(doSomethingOne("9"));
        futuresList.add(doSomethingOne("10"));

        CompletableFuture<Object> allOf = CompletableFuture.anyOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));

        System.out.println(allOf.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        anyOf();
    }
}
