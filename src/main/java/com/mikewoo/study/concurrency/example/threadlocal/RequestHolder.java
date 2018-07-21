package com.mikewoo.study.concurrency.example.threadlocal;

/**
 * {@link ThreadLocal}实现线程安全地请求数据存取
 * @author Eric Gui
 * @date 2018/7/21
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
