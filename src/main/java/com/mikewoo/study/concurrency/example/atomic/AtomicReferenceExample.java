package com.mikewoo.study.concurrency.example.atomic;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Eric Gui
 * @date 2018/7/20
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    public static AtomicReference<Integer> reference = new AtomicReference<>(0);

    public static void main(String[] args) {
        reference.compareAndSet(0, 2);
        reference.compareAndSet(0, 1);
        reference.compareAndSet(1, 3);
        reference.compareAndSet(2, 4);
        reference.compareAndSet(3, 5);
        log.info("count: {}", reference.get());

    }
}
