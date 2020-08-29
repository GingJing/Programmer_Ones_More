package com.mingyu.threadpool.copy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 复制JDK Future接口
 *
 * @author: GingJingDM
 * @date: 2020年 08月23日 18时23分
 * @version: 1.0
 */
public interface CopyFuture<V> {

    /**
     * 任务是否取消
     *
     * 尝试取消任务；
     * 1.如果当前任务已经完成或者任务已经被取消，则尝试任务会失败；
     * 2.如果任务还没被执行时调用了取消任务，则任务将永远不会被执行；
     * 3.如果任务已经开始运行，此时取消任务则参数mayInterruptIfRunning将决定是否要将正在执行任务的线程中断：true则标识中断，false标识不中断;
     * 4.当调用取消任务后，再调用idDone()方法后者会返回true，随后调用isCancelled()方法也会返回true;
     * 5.如果任务不能被取消，比如任务完成后已经被取消了，则该方法返回false。
     *
     * @param mayInterruptIfRunning 是否标识中断调用线程：true中断，false不中断
     * @return true 任务被取消
     */
    boolean cancel(boolean mayInterruptIfRunning);

    /**
     * 如果任务在执行完毕前被取消了则返回true，否则返回false
     * @return true 正常完毕前被取消，false 执行完毕后再取消
     */
    boolean isCancelled();

    /**
     * 任务完成后返回true
     * 1.正常完成；
     * 2.抛出异常而完成任务
     * 3.任务被取消
     *
     * @return true 任务完成返回
     */
    boolean isDone();

    /**
     * 等待获取异步任务计算结果
     *
     * @return 任务的计算结果
     * @throws java.util.concurrent.CancellationException   等待结果中任务被其他线程取消了
     * @throws InterruptedException                         当前线程被打断了
     * @throws ExecutionException                           计算过程中抛出异常则会抛出此异常
     */
    V get() throws InterruptedException, ExecutionException;

    /**
     * 在规定时间内获取异步计算结果
     *
     * @param timeout     超时时间
     * @param unit        时间单位
     * @return 异步计算结果
     * @throws java.util.concurrent.CancellationException 等待结果中任务被其他线程取消了
     * @throws InterruptedException                       当前线程被打断了
     * @throws ExecutionException                         计算过程中抛出异常则会抛出此异常
     * @throws TimeoutException                           等待超时
     */
    V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
