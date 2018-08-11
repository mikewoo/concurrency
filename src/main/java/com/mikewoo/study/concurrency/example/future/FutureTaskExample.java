package com.mikewoo.study.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.FutureTask;

/**
 * @author Eric Gui
 * @date 2018/8/11
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        });
        new Thread(futureTask).start();

        log.info("do something in main");
        Thread.sleep(2000);

        String result = futureTask.get();
        Optional.ofNullable(result).ifPresent(System.out::println);
    }
}
