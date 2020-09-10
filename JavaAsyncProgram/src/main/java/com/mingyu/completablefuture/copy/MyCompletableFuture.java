package com.mingyu.completablefuture.copy;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Copy JDK CompleteFuture {@link java.util.concurrent.CompletableFuture}
 *
 * @date: 2020/9/3 8:56
 * @author: GingJingDM
 * @version: 1.0
 */
public class MyCompletableFuture<T> implements Future<T>, MyCompletionStage<T> {
    @Override
    public <U> MyCompletionStage<U> thenApply(Function<? super T, ? extends U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenAccept(Consumer<? super T> action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenAcceptAsync(Consumer<? super T> action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenRun(Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenRunAsync(Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> thenRunAsync(Runnable action, Executor executor) {
        return null;
    }

    @Override
    public <U, V> MyCompletionStage<V> thenCombine(MyCompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return null;
    }

    @Override
    public <U, V> MyCompletionStage<V> thenCombineAsync(MyCompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return null;
    }

    @Override
    public <U, V> MyCompletionStage<V> thenCombineAsync(MyCompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<Void> thenAcceptBoth(MyCompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<Void> thenAcceptBothAsync(MyCompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<Void> thenAcceptBothAsync(MyCompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterBoth(MyCompletionStage<?> other, Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterBothAsync(MyCompletionStage<?> other, Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterBothAsync(MyCompletionStage<?> other, Runnable action, Executor executor) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> applyToEither(MyCompletionStage<? extends T> other, Function<? super T, U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> applyToEitherAsync(MyCompletionStage<? extends T> other, Function<? super T, U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> applyToEitherAsync(MyCompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> acceptEither(MyCompletionStage<? extends T> other, Consumer<? super T> action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> acceptEitherAsync(MyCompletionStage<? extends T> other, Consumer<? super T> action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> acceptEitherAsync(MyCompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterEither(MyCompletionStage<?> other, Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterEitherAsync(MyCompletionStage<?> other, Runnable action) {
        return null;
    }

    @Override
    public MyCompletionStage<Void> runAfterEitherAsync(MyCompletionStage<?> other, Runnable action, Executor executor) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> thenCompose(Function<? super T, ? extends MyCompletionStage<U>> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> thenComposeAsync(Function<? super T, ? extends MyCompletionStage<U>> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> thenComposeAsync(Function<? super T, ? extends MyCompletionStage<U>> fn, Executor executor) {
        return null;
    }

    @Override
    public MyCompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn) {
        return null;
    }

    @Override
    public MyCompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
        return null;
    }

    @Override
    public MyCompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
        return null;
    }

    @Override
    public MyCompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return null;
    }

    @Override
    public <U> MyCompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return null;
    }

    @Override
    public MyCompletableFuture<T> toCompletableFuture() {
        return null;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
