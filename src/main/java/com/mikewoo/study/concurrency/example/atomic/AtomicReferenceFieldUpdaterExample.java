package com.mikewoo.study.concurrency.example.atomic;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Eric Gui
 * @date 2018/7/20
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class, "count");

    @Getter
    private volatile int count = 100;


    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterExample example = new AtomicReferenceFieldUpdaterExample();

        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success, count: {}", example.count);
        }
        if (updater.compareAndSet(example, 100, 130)) {
            log.info("update success, count: {}", example.getCount());
        } else {
            log.info("update failed, count: {}", example.getCount());
        }

    }
}
