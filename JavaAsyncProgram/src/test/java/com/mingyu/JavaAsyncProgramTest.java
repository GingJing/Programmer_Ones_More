package com.mingyu;

import com.mingyu.completablefuture.spring.async.AsyncAnnotationDemo;
import com.mingyu.completablefuture.spring.executor.AsyncExecutorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月30日 00时02分
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaAsyncProgramTest {

    @Autowired
    private AsyncExecutorTest asyncExecutorTest;

    @Autowired
    private AsyncAnnotationDemo asyncAnnotationDemo;

    @Test
    public void testAsyncExecutor() {
        System.out.println(Thread.currentThread().getName() + " testAsyncExecutor start");
        asyncExecutorTest.printMessage();
        System.out.println(Thread.currentThread().getName() + " testAsyncExecutor end");
        asyncExecutorTest.shutdown();
    }

    @Test
    public void testAsyncAnnotationCompletableFuture() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + " testAsyncAnnotationCompletableFuture start");
        asyncAnnotationDemo.printMessages();
        CompletableFuture<String> result = asyncAnnotationDemo.asyncDoSth();
        result.whenComplete((s, t) -> {
            if (t == null) {
                System.out.println(Thread.currentThread().getName() + " " + s);
            } else {
                System.out.println("error: " + t.getLocalizedMessage());
            }
        });
        System.out.println(result.get());
        System.out.println(Thread.currentThread().getName() + " testAsyncAnnotationCompletableFuture end");
    }
}
