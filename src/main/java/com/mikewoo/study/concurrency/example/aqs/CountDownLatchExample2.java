package com.mikewoo.study.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Gui
 * @date 2018/7/22
 */
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
