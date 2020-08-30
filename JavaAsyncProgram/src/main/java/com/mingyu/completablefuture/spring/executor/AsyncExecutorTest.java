package com.mingyu.completablefuture.spring.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * executor测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 23时53分
 * @version: 1.0
 */
@Component
public class AsyncExecutorTest {
    @Autowired
    private TaskExecutor myTaskExecutor;

    private class MessagePrinterTask implements Runnable {
        // 消息
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + message);
//            try {
//                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName() + " " + message);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void printMessage() {
        for (int i = 0; i < 20; i++) {
            myTaskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    public void shutdown() {
        if (myTaskExecutor instanceof ThreadPoolTaskExecutor) {
            ((ThreadPoolTaskExecutor) myTaskExecutor).shutdown();
        }
    }
}
