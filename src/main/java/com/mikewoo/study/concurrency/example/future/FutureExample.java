package com.mikewoo.study.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Eric Gui
 * @date 2018/8/11
 */
@Slf4j
public class FutureExample {

    static class WorkCalllable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new WorkCalllable());
        log.info("do something in main");
        Thread.sleep(2000);
        String result = future.get();
        log.info("result is {}", result);
        executorService.shutdown();
    }
}
