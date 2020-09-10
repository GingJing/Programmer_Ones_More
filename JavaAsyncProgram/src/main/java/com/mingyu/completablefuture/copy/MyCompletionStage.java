package com.mingyu.completablefuture.copy;

import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Copy JDK CompletionStage{@link java.util.concurrent.CompletionStage}
 * 1.一个MyCompletionStage代表着一个异步计算节点，当另外一个MyCompletionStage计算节点完成后，
 * 当前MyCompletionStage会执行或者计算一个值；
 * 2.一个MyCompletionStage节点的计算或执行可以被表述为function、consumer、runnable（apply、
 * accept、run），具体的方法由此MyCompletionStage节点是否需要参数或者产生结果决定；
 * 举例：
 * {@code
 *      stage.thenApply(x -> square(x))
 *           .thenAccept(x -> System.out.print(x))
 *           .thenRun(() -> System.out.println())
 * }
 * 3.compose方法作用于MyCompletionStage节点本身而非节点的结果
 * 4.MyCompletionStage节点可以使用3种执行模式：
 *      1）默认执行
 *      2）默认异步执行
 *      3）用户自定义的线程执行器（即自定义Executor）
 * 5.一个MyCompletionStage节点的执行可以通过一到两个节点的执行完成来触发，
 * 一个节点依赖的其他节点通常用then前缀的方法来进行组织
 *
 * @date: 2020/9/3 8:54
 * @author: GingJingDM
 * @version: 1.0
 */
public interface MyCompletionStage<T> {

    /**
     * Returns a new CompletionStage that, when this stage completes
     * normally, is executed with this stage's result as the argument
     * to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenApply(Function<? super T,? extends U> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using this stage's default asynchronous
     * execution facility, with this stage's result as the argument to
     * the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenApplyAsync
    (Function<? super T,? extends U> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using the supplied Executor, with this
     * stage's result as the argument to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenApplyAsync
    (Function<? super T,? extends U> fn,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed with this stage's result as the argument
     * to the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenAccept(Consumer<? super T> action);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using this stage's default asynchronous
     * execution facility, with this stage's result as the argument to
     * the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using the supplied Executor, with this
     * stage's result as the argument to the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,
                                                 Executor executor);
    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, executes the given action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenRun(Runnable action);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, executes the given action using this stage's default
     * asynchronous execution facility.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenRunAsync(Runnable action);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, executes the given action using the supplied Executor.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> thenRunAsync(Runnable action,
                                              Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage both complete normally, is executed with the two
     * results as arguments to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the type of the other MyCompletionStage's result
     * @param <V> the function's return type
     * @return the new MyCompletionStage
     */
    public <U,V> MyCompletionStage<V> thenCombine
    (MyCompletionStage<? extends U> other,
     BiFunction<? super T,? super U,? extends V> fn);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, is executed using this stage's
     * default asynchronous execution facility, with the two results
     * as arguments to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the type of the other MyCompletionStage's result
     * @param <V> the function's return type
     * @return the new MyCompletionStage
     */
    public <U,V> MyCompletionStage<V> thenCombineAsync
    (MyCompletionStage<? extends U> other,
     BiFunction<? super T,? super U,? extends V> fn);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, is executed using the supplied
     * executor, with the two results as arguments to the supplied
     * function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the type of the other MyCompletionStage's result
     * @param <V> the function's return type
     * @return the new MyCompletionStage
     */
    public <U,V> MyCompletionStage<V> thenCombineAsync
    (MyCompletionStage<? extends U> other,
     BiFunction<? super T,? super U,? extends V> fn,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage both complete normally, is executed with the two
     * results as arguments to the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param <U> the type of the other MyCompletionStage's result
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<Void> thenAcceptBoth
    (MyCompletionStage<? extends U> other,
     BiConsumer<? super T, ? super U> action);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, is executed using this stage's
     * default asynchronous execution facility, with the two results
     * as arguments to the supplied action.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param <U> the type of the other MyCompletionStage's result
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<Void> thenAcceptBothAsync
    (MyCompletionStage<? extends U> other,
     BiConsumer<? super T, ? super U> action);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, is executed using the supplied
     * executor, with the two results as arguments to the supplied
     * function.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the type of the other MyCompletionStage's result
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<Void> thenAcceptBothAsync
    (MyCompletionStage<? extends U> other,
     BiConsumer<? super T, ? super U> action,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage both complete normally, executes the given action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterBoth(MyCompletionStage<?> other,
                                              Runnable action);
    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, executes the given action using
     * this stage's default asynchronous execution facility.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterBothAsync(MyCompletionStage<?> other,
                                                   Runnable action);

    /**
     * Returns a new MyCompletionStage that, when this and the other
     * given stage complete normally, executes the given action using
     * the supplied executor.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterBothAsync(MyCompletionStage<?> other,
                                                   Runnable action,
                                                   Executor executor);
    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed with the
     * corresponding result as argument to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> applyToEither
    (MyCompletionStage<? extends T> other,
     Function<? super T, U> fn);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed using this
     * stage's default asynchronous execution facility, with the
     * corresponding result as argument to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> applyToEitherAsync
    (MyCompletionStage<? extends T> other,
     Function<? super T, U> fn);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed using the
     * supplied executor, with the corresponding result as argument to
     * the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param fn the function to use to compute the value of
     * the returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> applyToEitherAsync
    (MyCompletionStage<? extends T> other,
     Function<? super T, U> fn,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed with the
     * corresponding result as argument to the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> acceptEither
    (MyCompletionStage<? extends T> other,
     Consumer<? super T> action);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed using this
     * stage's default asynchronous execution facility, with the
     * corresponding result as argument to the supplied action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> acceptEitherAsync
    (MyCompletionStage<? extends T> other,
     Consumer<? super T> action);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, is executed using the
     * supplied executor, with the corresponding result as argument to
     * the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> acceptEitherAsync
    (MyCompletionStage<? extends T> other,
     Consumer<? super T> action,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, executes the given action.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterEither(MyCompletionStage<?> other,
                                                Runnable action);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, executes the given action
     * using this stage's default asynchronous execution facility.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterEitherAsync
    (MyCompletionStage<?> other,
     Runnable action);

    /**
     * Returns a new MyCompletionStage that, when either this or the
     * other given stage complete normally, executes the given action
     * using the supplied executor.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param other the other MyCompletionStage
     * @param action the action to perform before completing the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<Void> runAfterEitherAsync
    (MyCompletionStage<?> other,
     Runnable action,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed with this stage as the argument
     * to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function returning a new MyCompletionStage
     * @param <U> the type of the returned MyCompletionStage's result
     * @return the MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenCompose
    (Function<? super T, ? extends MyCompletionStage<U>> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using this stage's default asynchronous
     * execution facility, with this stage as the argument to the
     * supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function returning a new MyCompletionStage
     * @param <U> the type of the returned MyCompletionStage's result
     * @return the MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenComposeAsync
    (Function<? super T, ? extends MyCompletionStage<U>> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * normally, is executed using the supplied Executor, with this
     * stage's result as the argument to the supplied function.
     *
     * See the {@link MyCompletionStage} documentation for rules
     * covering exceptional completion.
     *
     * @param fn the function returning a new MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the type of the returned MyCompletionStage's result
     * @return the MyCompletionStage
     */
    public <U> MyCompletionStage<U> thenComposeAsync
    (Function<? super T, ? extends MyCompletionStage<U>> fn,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * exceptionally, is executed with this stage's exception as the
     * argument to the supplied function.  Otherwise, if this stage
     * completes normally, then the returned stage also completes
     * normally with the same value.
     *
     * @param fn the function to use to compute the value of the
     * returned MyCompletionStage if this MyCompletionStage completed
     * exceptionally
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<T> exceptionally
    (Function<Throwable, ? extends T> fn);

    /**
     * Returns a new MyCompletionStage with the same result or exception as
     * this stage, that executes the given action when this stage completes.
     *
     * <p>When this stage is complete, the given action is invoked with the
     * result (or {@code null} if none) and the exception (or {@code null}
     * if none) of this stage as arguments.  The returned stage is completed
     * when the action returns.  If the supplied action itself encounters an
     * exception, then the returned stage exceptionally completes with this
     * exception unless this stage also completed exceptionally.
     *
     * @param action the action to perform
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<T> whenComplete
    (BiConsumer<? super T, ? super Throwable> action);

    /**
     * Returns a new MyCompletionStage with the same result or exception as
     * this stage, that executes the given action using this stage's
     * default asynchronous execution facility when this stage completes.
     *
     * <p>When this stage is complete, the given action is invoked with the
     * result (or {@code null} if none) and the exception (or {@code null}
     * if none) of this stage as arguments.  The returned stage is completed
     * when the action returns.  If the supplied action itself encounters an
     * exception, then the returned stage exceptionally completes with this
     * exception unless this stage also completed exceptionally.
     *
     * @param action the action to perform
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<T> whenCompleteAsync
    (BiConsumer<? super T, ? super Throwable> action);

    /**
     * Returns a new MyCompletionStage with the same result or exception as
     * this stage, that executes the given action using the supplied
     * Executor when this stage completes.
     *
     * <p>When this stage is complete, the given action is invoked with the
     * result (or {@code null} if none) and the exception (or {@code null}
     * if none) of this stage as arguments.  The returned stage is completed
     * when the action returns.  If the supplied action itself encounters an
     * exception, then the returned stage exceptionally completes with this
     * exception unless this stage also completed exceptionally.
     *
     * @param action the action to perform
     * @param executor the executor to use for asynchronous execution
     * @return the new MyCompletionStage
     */
    public MyCompletionStage<T> whenCompleteAsync
    (BiConsumer<? super T, ? super Throwable> action,
     Executor executor);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * either normally or exceptionally, is executed with this stage's
     * result and exception as arguments to the supplied function.
     *
     * <p>When this stage is complete, the given function is invoked
     * with the result (or {@code null} if none) and the exception (or
     * {@code null} if none) of this stage as arguments, and the
     * function's result is used to complete the returned stage.
     *
     * @param fn the function to use to compute the value of the
     * returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> handle
    (BiFunction<? super T, Throwable, ? extends U> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * either normally or exceptionally, is executed using this stage's
     * default asynchronous execution facility, with this stage's
     * result and exception as arguments to the supplied function.
     *
     * <p>When this stage is complete, the given function is invoked
     * with the result (or {@code null} if none) and the exception (or
     * {@code null} if none) of this stage as arguments, and the
     * function's result is used to complete the returned stage.
     *
     * @param fn the function to use to compute the value of the
     * returned MyCompletionStage
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> handleAsync
    (BiFunction<? super T, Throwable, ? extends U> fn);

    /**
     * Returns a new MyCompletionStage that, when this stage completes
     * either normally or exceptionally, is executed using the
     * supplied executor, with this stage's result and exception as
     * arguments to the supplied function.
     *
     * <p>When this stage is complete, the given function is invoked
     * with the result (or {@code null} if none) and the exception (or
     * {@code null} if none) of this stage as arguments, and the
     * function's result is used to complete the returned stage.
     *
     * @param fn the function to use to compute the value of the
     * returned MyCompletionStage
     * @param executor the executor to use for asynchronous execution
     * @param <U> the function's return type
     * @return the new MyCompletionStage
     */
    public <U> MyCompletionStage<U> handleAsync
    (BiFunction<? super T, Throwable, ? extends U> fn,
     Executor executor);

    /**
     * Returns a {@link MyCompletableFuture} maintaining the same
     * completion properties as this stage. If this stage is already a
     * MyCompletableFuture, this method may return this stage itself.
     * Otherwise, invocation of this method may be equivalent in
     * effect to {@code thenApply(x -> x)}, but returning an instance
     * of type {@code MyCompletableFuture}. A MyCompletionStage
     * implementation that does not choose to interoperate with others
     * may throw {@code UnsupportedOperationException}.
     *
     * @return the MyCompletableFuture
     * @throws UnsupportedOperationException if this implementation
     * does not interoperate with MyCompletableFuture
     */
    public MyCompletableFuture<T> toCompletableFuture();
}
