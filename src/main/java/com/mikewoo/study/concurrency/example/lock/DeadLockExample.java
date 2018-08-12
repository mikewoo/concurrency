package com.mikewoo.study.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个简单的死锁类
 * 当DeadLockExample类的对象flag==0时（td1），先锁定o1,睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==1的对象（td2）线程启动，先锁定o2,睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 * @author Eric Gui
 * @date 2018/8/11
 */
@Slf4j
public class DeadLockExample implements Runnable {
    private int flag = 0;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag: {}", flag);
        if (flag == 0) {
            execute(o1, o2);
        }
        if (flag == 1) {
            execute(o2, o1);
        }
    }

    public static void main(String[] args) {
        DeadLockExample td1 = new DeadLockExample();
        DeadLockExample td2 = new DeadLockExample();
        td1.flag = 0;
        td2.flag = 1;
        new Thread(td1).start();
        new Thread(td2).start();
    }

    private void execute(Object obj1, Object obj2) {
        synchronized (obj1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                log.info("flag: {}", flag);
            }
        }
    }
}
