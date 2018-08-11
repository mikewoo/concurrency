package com.mikewoo.study.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Gui
 * @date 2018/8/11
 */
@Slf4j
public class ThreadPoolExample5 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            log.info("schdule task running...");
        }, 1, 3, TimeUnit.SECONDS);

        // executorService.shutdown();
    }
}
