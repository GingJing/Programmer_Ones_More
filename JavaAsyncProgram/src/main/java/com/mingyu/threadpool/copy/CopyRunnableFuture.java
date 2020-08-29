package com.mingyu.threadpool.copy;

/**
 * 复制JDK RunnableFuture类
 *
 * @author: GingJingDM
 * @date: 2020年 08月23日 18时45分
 * @version: 1.0
 */
public interface CopyRunnableFuture<V> extends Runnable, CopyFuture<V> {
    /**
     * Sets this Future to the result of its computation
     * unless it has been cancelled.
     */
    @Override
    void run();
}