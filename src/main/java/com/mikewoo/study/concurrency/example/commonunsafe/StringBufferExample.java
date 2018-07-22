package com.mikewoo.study.concurrency.example.commonunsafe;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>{@link StringBuffer}多线程测试实例</p>
 * <p>StringBuffer在进行字符串操作时使用synchronized进行同步处理，所以是线程安全的，但由于同一时间只有一个线程能操作StringBuffer，所以效率比较低。</p>
 * @author Eric Gui
 * @date 2018/7/22
 */
@Slf4j
@ThreadSafe
public class StringBufferExample {

    // 请求总数
    public static final int clientTotal = 5000;

    // 同时并发执行的线程数
    public static final int threadTotal = 200;

    public static final StringBuffer stringBuffer = new StringBuffer();

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
        log.info("size: {}", stringBuffer.length());
    }

    public static void update() {
        stringBuffer.append("1");
    }
}
