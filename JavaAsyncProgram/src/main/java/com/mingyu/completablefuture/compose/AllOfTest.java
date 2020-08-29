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
public class AllOfTest {

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

    public static void allOf() throws InterruptedException, ExecutionException {
        List<CompletableFuture<String>> futuresList = new ArrayList<>();
        futuresList.add(doSomethingOne("1"));
        futuresList.add(doSomethingOne("2"));
        futuresList.add(doSomethingOne("3"));
        futuresList.add(doSomethingOne("4"));
        futuresList.add(doSomethingOne("5"));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));

        System.out.println(allOf.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        allOf();
    }
}
