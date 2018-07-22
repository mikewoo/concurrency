package com.mikewoo.study.concurrency.example.commonunsafe;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Eric Gui
 * @date 2018/7/22
 */
@Slf4j
@ThreadSafe
public class DateFormateExample3 {

    private final static ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    // 请求总数
    public static final int clientTotal = 5000;

    // 同时并发执行的线程数
    public static final int threadTotal = 200;

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
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
    }

    public static void update() {
        try {
            df.get().parse("2018-07-22 12:43:12");
        } catch (ParseException e) {
            log.error("parse exception", e);
        }
    }


}
