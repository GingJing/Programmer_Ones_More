package com.mingyu.threadpool.copy;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 复制JDK CopyFutureTask类
 *
 * @author: GingJingDM
 * @date: 2020年 08月23日 18时45分
 * @version: 1.0
 */
public class CopyFutureTask<V> implements CopyRunnableFuture<V> {

    /**
     * NEW -> COMPLETING -> NORMAL：正常终止的流程转换
     * NEW -> COMPLETING -> EXCEPTIONAL：执行过程中发生异常的流程转换
     * NEW -> COMPLETED：任务还未开始就被取消
     * NEW -> INTERRUPTING -> INTERRUPTED：任务被中断
     *
     * 任务状态， volatile关键字解决多线程下内存不可见问题
     */
    private volatile int state;

    /** 任务初始化状态 */
    private static final int NEW          = 0;

    /** 任务完成过程中的一个状态 */
    private static final int COMPLETING   = 1;

    /** 任务正常终止时 */
    private static final int NORMAL       = 2;

    /** 任务执行异常后 */
    private static final int EXCEPTIONAL  = 3;

    /** 任务被取消后 */
    private static final int CANCELLED    = 4;

    /** 任务完成过程中 */
    private static final int INTERRUPTING = 5;

    /** 任务被中断后 */
    private static final int INTERRUPTED  = 6;

    /** 有返回值的可执行任务 */
    private Callable<V> callable;
    /** 任务运行的结果，通过get()系列方法获取，没有被volatile修饰，因为state被volatile修饰了（non-volatile, protected by state reads/writes） */
    private Object outcome;
    /** 运行任务的线程; CASed during run() */
    private volatile Thread runner;
    /** Treiber stack of waiting threads */
    private volatile CopyFutureTask.WaitNode waiters;

    /**
     * Returns result or throws exception for completed task.
     *
     * @param s completed state value
     */
    @SuppressWarnings("unchecked")
    private V report(int s) throws ExecutionException {
        Object x = outcome;
        if (s == NORMAL) {
            return (V)x;
        }
        if (s >= CANCELLED) {
            throw new CancellationException();
        }
        throw new ExecutionException((Throwable)x);
    }

    /**
     * Creates a {@code CopyFutureTask} that will, upon running, execute the
     * given {@code Callable}.
     *
     * @param  callable the callable task
     * @throws NullPointerException if the callable is null
     */
    public CopyFutureTask(Callable<V> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        this.callable = callable;
        // state被volatile修饰，保证了写入state的值时callable也能被刷入内存，以避免多线程内存不可见问题
        this.state = NEW;
    }

    /**
     * Creates a {@code CopyFutureTask} that will, upon running, execute the
     * given {@code Runnable}, and arrange that {@code get} will return the
     * given result on successful completion.
     *
     * @param runnable the runnable task
     * @param result the result to return on successful completion. If
     * you don't need a particular result, consider using
     * constructions of the form:
     * {@code Future<?> f = new CopyFutureTask<Void>(runnable, null)}
     * @throws NullPointerException if the runnable is null
     */
    public CopyFutureTask(Runnable runnable, V result) {
        // 将Runnable类任务适配为Callable类任务
        this.callable = Executors.callable(runnable, result);
        // state被volatile修饰，保证了写入state的值时callable也能被刷入内存，以避免多线程内存不可见问题
        this.state = NEW;
    }

    /*
     * Executors.callable(Runnable task, T result)方法使用了适配器模式，源码如下：
     *
     * public static <T> Callable<T> callable(Runnable task, T result) {
     *         if (task == null) {
     *             throw new NullPointerException();
     *         }
     *         return new RunnableAdapter<T>(task, result);
     *     }
     *
     *     static final class RunnableAdapter<T> implements Callable<T> {
     *         final Runnable task;
     *         final T result;
     *
     *         public RunnableAdapter(Runnable task, T result) {
     *             this.task = task;
     *             this.result = result;
     *         }
     *
     *         @Override
     *         public T call() throws Exception {
     *             task.run();
     *             return result;
     *         }
     *     }
     */

    /**
     * 任务是否取消
     * <p>
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
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        // 如果任务状态为NEW则使用CAS设置任务状态为INTERRUPTING或者CANCELLED
        if (!(state == NEW &&
                UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
                        mayInterruptIfRunning ? INTERRUPTING : CANCELLED))) {
            return false;
        }
        try {    // in case call to interrupt throws exception
            if (mayInterruptIfRunning) {
                try {
                    Thread t = runner;
                    if (t != null) {
                        t.interrupt();
                    }
                } finally { // final state
                    UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
                }
            }
        } finally {
            // 移除并激活因等待结果而阻塞的线程
            finishCompletion();
        }
        return true;
    }

    /**
     * 如果任务在执行完毕前被取消了则返回true，否则返回false
     *
     * @return true 正常完毕前被取消，false 执行完毕后再取消
     */
    @Override
    public boolean isCancelled() {
        return state >= CANCELLED;
    }

    /**
     * 任务完成后返回true
     * 1.正常完成；
     * 2.抛出异常而完成任务
     * 3.任务被取消
     *
     * @return true 任务完成返回
     */
    @Override
    public boolean isDone() {
        return state != NEW;
    }

    /**
     * 等待获取异步任务计算结果
     *
     * @return 任务的计算结果
     * @throws CancellationException 等待结果中任务被其他线程取消了
     * @throws InterruptedException  当前线程被打断了
     * @throws ExecutionException    计算过程中抛出异常则会抛出此异常
     */
    @Override
    public V get() throws InterruptedException, ExecutionException {
        // 获取状态
        int s = state;
        if (s <= COMPLETING) {
            // 等待任务终止
            s = awaitDone(false, 0L);
        }
        // 返回结果
        return report(s);
    }

    /**
     * 在规定时间内获取异步计算结果
     *
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 异步计算结果
     * @throws CancellationException 等待结果中任务被其他线程取消了
     * @throws InterruptedException  当前线程被打断了
     * @throws ExecutionException    计算过程中抛出异常则会抛出此异常
     * @throws TimeoutException      等待超时
     */
    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (unit == null) {
            throw new NullPointerException();
        }
        int s = state;
        if (s <= COMPLETING &&
                (s = awaitDone(true, unit.toNanos(timeout))) <= COMPLETING) {
            throw new TimeoutException();
        }
        return report(s);
    }

    protected void done() { }

    protected void set(V v) {
        // 尝试设置state状态为COMPLETING，如果成功则把任务正常执行结果设置给outcome变量
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            outcome = v;
            // final state -> 设置任务状态为NORMAL
            UNSAFE.putOrderedInt(this, stateOffset, NORMAL);
            finishCompletion();
        }
    }

    protected void setException(Throwable t) {
        // 尝试设置state状态为COMPLETING，如果成功则把异常信息设置给outcome变量
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            outcome = t;
            // final state -> 设置任务状态为EXCEPTIONAL
            UNSAFE.putOrderedInt(this, stateOffset, EXCEPTIONAL);
            // 唤醒所有因为等待结果而被阻塞的线程
            finishCompletion();
        }
    }

    @Override
    public void run() {
        // 1.如果任务不是初始化的NEW状态，或者使用CAS设置runner为当前线程失败，则直接返回
        if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset,
                        null, Thread.currentThread())) {
            return;
        }
        // 2.如果任务不为null，并且任务状态为NEW，则执行任务
        try {
            Callable<V> c = callable;
            if (c != null && state == NEW) {
                V result;
                boolean ran;
                try {
                    // 2.1执行任务，成功则设置标记ran为true
                    result = c.call();
                    ran = true;
                } catch (Throwable ex) {
                    // 2.2执行任务出现异常，则标记false，并且设置异常
                    result = null;
                    ran = false;
                    setException(ex);
                }
                // 3.任务执行正常，则设置结果
                if (ran) {
                    set(result);
                }
            }
        } finally {
            // runner must be non-null until state is settled to
            // prevent concurrent calls to run()
            runner = null;
            // state must be re-read after nulling runner to prevent
            // leaked interrupts
            int s = state;
            // 4.为了保证调用cancel(true)的线程在该run方法返回前中断任务执行的线程
            if (s >= INTERRUPTING) {
                handlePossibleCancellationInterrupt(s);
            }
        }
    }

    /**
     * Executes the computation without setting its result, and then
     * resets this future to initial state, failing to do so if the
     * computation encounters an exception or is cancelled.  This is
     * designed for use with tasks that intrinsically execute more
     * than once.
     *
     * @return {@code true} if successfully run and reset
     */
    protected boolean runAndReset() {
        // 1.如果任务不是初始化的NEW状态，或者使用CAS设置runner为当前线程失败，则直接返回
        if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset,
                        null, Thread.currentThread())) {
            return false;
        }
        boolean ran = false;
        int s = state;
        try {
            // 2.如果任务不为null，并且任务状态为NEW，则执行任务
            Callable<V> c = callable;
            if (c != null && s == NEW) {
                try {
                    // 2.1执行任务，成功则设置标记ran为true
                    c.call(); // don't set result
                    ran = true;
                } catch (Throwable ex) {
                    // 2.2执行任务若出现异常则设置异常
                    setException(ex);
                }
            }
        } finally {
            // runner must be non-null until state is settled to
            // prevent concurrent calls to run()
            runner = null;
            // state must be re-read after nulling runner to prevent
            // leaked interrupts
            s = state;
            if (s >= INTERRUPTING) {
                handlePossibleCancellationInterrupt(s);
            }
        }
        return ran && s == NEW;
    }

    private void handlePossibleCancellationInterrupt(int s) {
        // It is possible for our interrupter to stall before getting a
        // chance to interrupt us.  Let's spin-wait patiently.
        // 为了保证调用cancel在该run方法返回前中断任务执行的线程
        // 这里使用Thread.yield()让run方法执行线程让出CPU执行权，以便让cancel(true)的线程执行cancel(true)中的代码中断任务线程
        if (s == INTERRUPTING) {
            while (state == INTERRUPTING) {
                // wait out pending interrupt
                Thread.yield();
            }
        }

        // assert state == INTERRUPTED;

        // We want to clear any interrupt we may have received from
        // cancel(true).  However, it is permissible to use interrupts
        // as an independent mechanism for a task to communicate with
        // its caller, and there is no way to clear only the
        // cancellation interrupt.
        //
        // Thread.interrupted();
    }

    /**
     * 用Treiber stack实现的无锁栈，栈顶元素用waiters代表。
     * 简单的链表节点，栈用来记录所有等待任务结果的被阻塞的线程
     */
    static final class WaitNode {
        volatile Thread thread;
        volatile CopyFutureTask.WaitNode next;
        WaitNode() { thread = Thread.currentThread(); }
    }

    /**
     * Removes and signals all waiting threads, invokes done(), and
     * nulls out callable.
     */
    private void finishCompletion() {
        // assert state > COMPLETING;
        for (CopyFutureTask.WaitNode q; (q = waiters) != null;) {
            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
                for (;;) {
                    // 唤醒当前q节点对应的线程
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        LockSupport.unpark(t);
                    }
                    // 获取q的下一个节点
                    CopyFutureTask.WaitNode next = q.next;
                    if (next == null) {
                        break;
                    }
                    // unlink to help gc
                    q.next = null;
                    q = next;
                }
                break;
            }
        }

        // 所有阻塞的线程都被唤醒后，调用done方法，默认空实现
        done();

        // to reduce footprint
        callable = null;
    }

    /**
     * Awaits completion or aborts on interrupt or timeout.
     *
     * @param timed true if use timed waits
     * @param nanos time to wait, if timed
     * @return state upon completion
     */
    private int awaitDone(boolean timed, long nanos)
            throws InterruptedException {
        // 设置超时时间
        final long deadline = timed ? System.nanoTime() + nanos : 0L;
        CopyFutureTask.WaitNode q = null;
        boolean queued = false;
        // 循环，等待任务完成
        for (;;) {
            if (Thread.interrupted()) {
                // 任务被中断，则移除等待线程节点，抛出异常
                removeWaiter(q);
                throw new InterruptedException();
            }

            int s = state;
            if (s > COMPLETING) {
                // 此状态的任务为：NORMAL，EXCEPTIONAL，CANCELLED，INTERRUPTED
                if (q != null) {
                    q.thread = null;
                }
                return s;
            }
            // cannot time out yet
            else if (s == COMPLETING) {
                Thread.yield();
            }
            else if (q == null) {
                // 为当前线程创建节点
                q = new CopyFutureTask.WaitNode();
            }
            else if (!queued) {
                // 添加当前线程节点到链表
                queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                        q.next = waiters, q);
            }
            else if (timed) {
                // 设置了超时时间
                nanos = deadline - System.nanoTime();
                if (nanos <= 0L) {
                    removeWaiter(q);
                    return state;
                }
                LockSupport.parkNanos(this, nanos);
            }
            else {
                // 无参数的get方法，即无超时时间
                LockSupport.park(this);
            }
        }
    }

    /**
     * Tries to unlink a timed-out or interrupted wait node to avoid
     * accumulating garbage.  Internal nodes are simply unspliced
     * without CAS since it is harmless if they are traversed anyway
     * by releasers.  To avoid effects of unsplicing from already
     * removed nodes, the list is retraversed in case of an apparent
     * race.  This is slow when there are a lot of nodes, but we don't
     * expect lists to be long enough to outweigh higher-overhead
     * schemes.
     */
    private void removeWaiter(CopyFutureTask.WaitNode node) {
        if (node != null) {
            node.thread = null;
            retry:
            for (;;) {          // restart on removeWaiter race
                for (CopyFutureTask.WaitNode pred = null, q = waiters, s; q != null; q = s) {
                    s = q.next;
                    if (q.thread != null) {
                        pred = q;
                    }
                    else if (pred != null) {
                        pred.next = s;
                        // check for race
                        if (pred.thread == null) {
                            continue retry;
                        }
                    }
                    else if (!UNSAFE.compareAndSwapObject(this, waitersOffset,
                            q, s)) {
                        continue retry;
                    }
                }
                break;
            }
        }
    }

    /** state变量的偏移地址 */
    private static final sun.misc.Unsafe UNSAFE;

    /** state变量的偏移地址 */
    private static final long stateOffset;

    /** runner变量的偏移地址 */
    private static final long runnerOffset;

    /** waiters变量的偏移地址 */
    private static final long waitersOffset;

    static {
        // 获取几个变量在CopyFutureTask对象内的内存地址偏移量，以便实现用UNSAFE的CAS操作来操作这些变量
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = CopyFutureTask.class;
            stateOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("state"));
            runnerOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("runner"));
            waitersOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }


}
