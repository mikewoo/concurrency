package com.mikewoo.study.concurrency.example.lock;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Eric Gui
 * @date 2018/7/28
 */
@Slf4j
@ThreadSafe
public class LockExample3 {

    private static Map<Long, Data> datas = new ConcurrentHashMap<>();

    private static final Lock lock = new ReentrantLock();

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final Lock readLock = readWriteLock.readLock();

    private static final Lock writeLock = readWriteLock.writeLock();

    public static Data getData(Long id) {
        readLock.lock();
        try {
            return datas.get(id);
        } finally {
            readLock.unlock();
        }
    }

    public static Set<Long> getKeys() {
        readLock.lock();
        try {
            return datas.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public static void put(Long id, Data data) {
        writeLock.lock();
        try {
            datas.put(id, data);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        put(1L, new Data(1L, "ReentrantReadWriteLock writeLock"));

        Data data1 = getData(1L);
        log.info("get data: {}", data1);
    }


}
