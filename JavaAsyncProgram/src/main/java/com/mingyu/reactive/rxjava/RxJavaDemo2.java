package com.mingyu.reactive.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * rxjava demo2
 *
 * @date: 2020/9/24 9:04
 * @author: GingJingDM
 * @version: 1.0
 */
public class RxJavaDemo2 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            return "done";
        }).observeOn(Schedulers.single())
          .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("cost: " + (System.currentTimeMillis() - start));

        Thread.sleep(2000);
    }
}
