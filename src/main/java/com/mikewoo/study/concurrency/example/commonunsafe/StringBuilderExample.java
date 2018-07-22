package com.mikewoo.study.concurrency.example.commonunsafe;

import com.mikewoo.study.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>{@link StringBuilder}多线程测试实例</p>
 * <p>StringBuilder在进行字符串操作时由于没有进行同步处理，所以是线程不安全的。</p>
 * @author Eric Gui
 * @date 2018/7/22
 */
@Slf4j
@NotThreadSafe
public class StringBuilderExample {

    // 请求总数
    public static final int clientTotal = 5000;

    // 同时并发执行的线程数
    public static final int threadTotal = 200;

    public static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception: {}", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", stringBuilder.length());
    }

    public static void update() {
        stringBuilder.append("1");
    }
}
