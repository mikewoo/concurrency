package com.mikewoo.study.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
public class SynchronizedExample1 {

    // 同步代码块，修饰一个对象
    public void test1(int id) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("[{}] test1 - exec count: {}", id, i);
            }
        }
    }

    // 同步方法
    public synchronized void test2(int id) {
        for (int i = 0; i < 10; i++) {
            log.info("[{}] test2 - exec count: {}", id, i);
        }
    }

    public static void main(String[] args) {
        // syncTest1();
        // syncTest2();
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test1(2);
        });
    }

    private static void syncTest1() {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });

        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });
    }
    private static void syncTest2() {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test2(1);
        });

        executorService.execute(() -> {
            synchronizedExample1.test2(1);
        });
    }
}
